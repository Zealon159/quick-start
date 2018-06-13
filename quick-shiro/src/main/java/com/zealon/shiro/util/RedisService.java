package com.zealon.shiro.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;


/**
 * redis 服务组件
 * 
 */
@Service("redisService")
public class RedisService {

	@Autowired
	private RedisTemplate<String, ?> redisTemplate;
	
	/**
	 * 存储key
	 */
	public boolean set(final String key, final Object value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), SerializeUtils.serialize(value));
                return true;
            }
	    });
		return result;
	}

	/**
	 * 获取Object类型key
	 */
	public Object getObject(final String key){
		Object result = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return  SerializeUtils.deserialize(value);
            }
	    });
		return result;
	}
	
	/**
	 * 获取String类型key
	 */
	public String get(final String key){
		String result = redisTemplate.execute(new RedisCallback<String>() {
            
            public String doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
	    });
		return result;
	}

	
	public boolean expire(final String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

//	
//	public <T> boolean setList(String key, List<T> list) {
//		String value = JSONUtil.toJson(list);
//		return set(key,value);
//	}
//
//	
//	public <T> List<T> getList(String key,Class<T> clz) {
//		String json = get(key);
//		if(json!=null){
//			List<T> list = JSONUtil.toList(json, clz);
//			return list;
//		}
//		return null;
//	}
//
//	
//	public long lpush(final String key, Object obj) {
//		final String value = JSONUtil.toJson(obj);
//		long result = redisTemplate.execute(new RedisCallback<Long>() {
//            
//            public Long doInRedis(RedisConnection connection) throws DataAccessException {
//            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
//                return count;
//            }
//	    });
//		return result;
//	}
//
//	
//	public long rpush(final String key, Object obj) {
//		final String value = JSONUtil.toJson(obj);
//		long result = redisTemplate.execute(new RedisCallback<Long>() {
//            
//            public Long doInRedis(RedisConnection connection) throws DataAccessException {
//            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
//                return count;
//            }
//	    });
//		return result;
//	}

	
	public String lpop(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res =  connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
	    });
		return result;
	}
	
	public void delete(final String key){
		redisTemplate.delete(key);
	}
	
}