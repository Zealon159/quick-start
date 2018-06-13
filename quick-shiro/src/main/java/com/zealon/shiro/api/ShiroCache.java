package com.zealon.shiro.api;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.zealon.shiro.util.RedisService;
import com.zealon.shiro.util.SerializeUtils;

/**
 * shiro缓存管理
 *
 */
@SuppressWarnings({ "unchecked", "unused" })
public class ShiroCache<K, V> implements Cache<K, V> {

    private Charset charset;
    public static final String REDIS_SHIRO_CACHE = "";//biz-shiro-cache:
    private String cacheKey;
    private RedisTemplate<K, V> redisTemplate;
    private long globExpire = 120; //分
    
    @Autowired
    private RedisService redisService;
    
    @SuppressWarnings("rawtypes")
    public ShiroCache(String name, RedisTemplate client) {
        this.cacheKey = REDIS_SHIRO_CACHE + name +":";
        this.redisTemplate = client;
    }

    @Override
    public V get(K key) throws CacheException {
        redisTemplate.boundValueOps(getCacheKey(key)).expire(globExpire, TimeUnit.MINUTES);
        return redisTemplate.boundValueOps(getCacheKey(key)).get();
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V old = get(key);
        //redisTemplate.boundValueOps(getCacheKey(key)).set(value);
        set(getCacheKey(key).toString(), value);
        //redisTemplate.opsForValue().set(getCacheKey(key), value);
        return old;
    }

    @Override
    public V remove(K key) throws CacheException {
        V old = get(key);
        redisTemplate.delete(getCacheKey(key));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for (K s : set) {
            list.add(get(s));
        }
        return list;
    }

    private K getCacheKey(Object k) {
    	
        return (K) (this.cacheKey + k);
    }
    
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
}
