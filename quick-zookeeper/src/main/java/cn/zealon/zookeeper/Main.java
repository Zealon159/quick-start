package cn.zealon.zookeeper;

import cn.zealon.zookeeper.common.ZookeeperConstant;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: Zealon
 * @Date: 2018-06-22 10:49
 */
public class Main {

    public static void main(String[] args){
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZookeeperConstant.CONNECT_ADDR,
                new ExponentialBackoffRetry(ZookeeperConstant.BASE_SLEEP_TIME_MS, ZookeeperConstant.MAX_RETRIES));

        client.start();

        String path = "/path";

        String result = create(client,path,"root");

        String text = getNodeText(client,path);
        System.out.println(text);

        Map<String,Object> nodeStat = getNodeStat(client,path);
        System.out.println(nodeStat.toString());

        modify(client,path,"myRoot");

        List<String> nodes = getChildrenNodes(client,"/");
        for(String node : nodes){
            String nodeText = getNodeText(client,"/"+node);
            System.out.println(node+":"+nodeText);
        }

        delete(client,path);

        client.close();
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
            Stat exists = client.checkExists().forPath(path);
            if(exists==null){
                result = client.create().forPath(path,text.getBytes());
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
            client.delete().forPath(path);
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
            client.delete().deletingChildrenIfNeeded().forPath(path);
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
    public static List<String> getChildrenNodes(CuratorFramework client,String path){
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

}
