package zealon.redis;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class App 
{
	private static Jedis jedis;
	private static ShardedJedis shard;
	private static ShardedJedisPool pool;
	
    public static void main( String[] args ){
    	jedis = RedisUtil.getJedis();
    	Set<String> keys = jedis.keys("*");
    
//        for(String key : keys){
//       		System.out.println(key+":"+jedis.get(key));
//        }
    	
//    	setUpBeforeClass();
//        //jedis.set("name", "hhh");
//    	
//        //jedis.mset("this","this","is","is","guogongzhuang","郭公庄","station","station");
//        Set<String> keys = jedis.keys("*");
//        for(String key : keys){
//        	System.out.println(key+":"+jedis.get(key));
//        }
//        tearDownAfterClass();
    }
	

	public static void setUpBeforeClass(){
		//单节点
		jedis = new Jedis("192.168.2.163", 6379);
		//分片
		List<JedisShardInfo> shards = Arrays.asList(
				new JedisShardInfo("192.168.2.163", 6379));
		shard = new ShardedJedis(shards);
		GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
		goConfig.setMaxTotal(100);
		goConfig.setMaxIdle(20);
		goConfig.setMaxWaitMillis(-1);
		goConfig.setTestOnBorrow(true);
		pool = new ShardedJedisPool(goConfig, shards);
	}
	

	public static void tearDownAfterClass() {
		jedis.disconnect();
		shard.disconnect();
		pool.destroy();
	}
	
	public void shardPool(){
		ShardedJedis sj = pool.getResource();
		
	}
}
