package cn.zealon.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.*;

/**
 * @Author: zealon
 * @Version: 1.0
 */
public class RedisConfig {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 实例化 ValueOperations 对象,可以使用 String 操作
     */
    @Bean
    public ValueOperations<String, String> valueOperations() {
        return redisTemplate.opsForValue();
    }

    /**
     * 实例化 ListOperations 对象,可以使用 List 操作
     * @return
     */
    @Bean
    public ListOperations<String, String> listOperations() {
        return redisTemplate.opsForList();
    }

    /**
     * 实例化 SetOperations 对象,可以使用 Set 操作
     */
    @Bean
    public SetOperations<String, String> setOperations() {
        return redisTemplate.opsForSet();
    }

    /**
     * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
     */
    @Bean
    public ZSetOperations<String, String> zSetOperations() {
        return redisTemplate.opsForZSet();
    }
}
