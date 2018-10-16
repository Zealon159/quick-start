package cn.zealon.redis;

import cn.zealon.common.utils.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * redis 服务组件
 *
 */
@Service
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
     * 设置过期时间
     * @param key
     * @param expire 毫秒
     * @return
     */
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void delete(final String key){
        redisTemplate.delete(key);
    }
}
