package zealon.redis;

import redis.clients.jedis.Jedis;

public class CacheData {
	
	private static Jedis jedis;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String key = "财务总监";
		
		System.out.println(getCacheJson(key)); 
	}
	
	/**
	 * 获取缓存数据
	 * @param key
	 * @return
	 */
	public static String getCacheJson(String key){
		String keyJson = RedisUtil.getString(key);
		
		if(keyJson==null){
			System.out.println("关系数据库获取数据，缓存到redis");
			keyJson = "{\"menus_"+key+"\":\"[{\"menuName\":\"百度\",\"menuURI\":\"http://www.baidu.com\"},{\"menuName\":\"新浪\",\"menuURI\":\"http://www.sina.com\"}]\"}";
			RedisUtil.setString(key, keyJson);
		}
		return keyJson;
	}
	
	//public static 
	 
}
