package cn.zealon.zookeeper.curator;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: Zealon
 * @Date: 2018-08-29 14:38
 */
public class CuratorUtil {

    public static final String connectString = "47.104.241.41:2181";
    /**
     * 会话超时时间（毫秒）
     */
    public static final int sessionTimeoutMs = 5000;
    /**
     * 链接超时时间
     */
    public static final int connectionTimeoutMs = 3000;

    public static void main(String[] args){

        CuratorFramework client = createSessoinByFluent();
        client.start();

        //create(client,"/db_config/dev","{\"username\":\"root\",\"password\":\"pass_dev\"}");
        //create(client,"/db_config/pro","{\"username\":\"root\",\"password\":\"pass_pro\"}");

        //delete(client,"/db_config/dev");

        //System.out.println(getNodeText(client,"/db_config/pro"));

        nodeCache(client,"/db_config/pro");
        modify(client,"/db_config/pro","aaaa");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 构造器方式创建会话
     */
    public static CuratorFramework createSessoin(){
        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        //创建zookeeper客户端
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString,sessionTimeoutMs,connectionTimeoutMs,retryPolicy);

        return client;
    }

    /**
     * Fluent接口风格创建会话
     */
    public static CuratorFramework createSessoinByFluent(){
        //重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        //创建zookeeper客户端
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(retryPolicy)
                .namespace("base") //隔离命名空间（每个业务分配一个独立的命名空间）
                .build();
        return client;
    }

    /**
     * 创建节点
     * @param client
     * @param path
     * @param text
     */
    public static String create(CuratorFramework client,String path,String text) {
        String result = "";
        try {
            if(client.checkExists().forPath(path) == null){
                result = client.create()
                        .forPath(path,text.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除节点
     * @param client
     * @param path
     */
    public static void delete(CuratorFramework client,String path) {
        try {
            client.delete()
                    .guaranteed()
                    .forPath(path);
            System.out.println(path+" 已删除！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有节点
     * @param client
     * @param path
     */
    public static void deleteAll(CuratorFramework client,String path) {
        try {
            client.delete()
                    .guaranteed()
                    .deletingChildrenIfNeeded()
                    .forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取节点内容
     * @param client
     * @param path
     * @return
     */
    public static String getNodeText(CuratorFramework client,String path){
        String text = null;
        try {
            text = new String(client.getData().forPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 读取所有子节点
     * @param client
     * @param path
     * @return
     */
    public static List<String> getChildrenNodes(CuratorFramework client, String path){
        List<String> text = null;
        try {
            text = client.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * 读取节点内容并获取Stat
     * @param client
     * @param path
     * @return
     */
    public static Map<String,Object> getNodeStat(CuratorFramework client, String path){
        Map<String,Object> result = new HashMap<String,Object>();
        try {
            Stat stat = new Stat();
            byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
            result.put("stat",stat);
            result.put("text",new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新节点内容
     * @param client
     * @param path
     */
    public static void modify(CuratorFramework client, String path,String text){
        try {
            client.setData().forPath(path,text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 节点监听
     * @param client
     * @param path
     */
    public static void nodeCache(CuratorFramework client, String path){
        NodeCache nodeCache = new NodeCache(client,path,false);
        try {
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("Node data update. new data:"
                        + new String(nodeCache.getCurrentData().getData()));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
