package cn.zealon.shiro.api;

import java.io.Serializable;
import javax.annotation.Resource;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis实现共享session
 */
@Component
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        logger.info("创建session:{}", session.getId());
        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
    	logger.info("读取session:{}",sessionId);
        Session session = super.doReadSession(sessionId);
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
    }

    // 删除session
    @Override
    protected void doDelete(Session session) {
        logger.info("删除session:{}",session.getId());
        super.doDelete(session);
    }
    
    //protected Collection<Session> getActiveSessions
}

//
//@Component
//public class RedisSessionDAO extends AbstractSessionDAO {
//	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
//    /**
//     * shiro-redis的session对象前缀
//     */
//	@Resource
//    private RedisTemplate<String, Object> redisTemplate;
//    // 0 - never expire
//    private int expire = 3600000;
//    
//    /**
//     * The Redis key prefix for the sessions biz-shiro-session:shiro_market_redis_session:
//     */
//    public static String keyPrefix = "biz-shiro-redis-session:";
//    
//    @Override
//    public void update(Session session) throws UnknownSessionException {
//        this.saveSession(session);
//    }
//    
//    /**
//     * save session
//     * @param session
//     * @throws UnknownSessionException
//     */
//    private void saveSession(Session session) throws UnknownSessionException{
//        if(session == null || session.getId() == null){
//            logger.error("session or session id is null");
//            return;
//        }
//        
//        String key = session.getId().toString();
//        session.setTimeout(expire);        
//        redisTemplate.opsForValue().set(keyPrefix+key, session, expire, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void delete(Session session) {
//        if(session == null || session.getId() == null){
//            logger.error("session or session id is null");
//            return;
//        }
//        redisTemplate.delete(keyPrefix+session.getId().toString());
//
//    }
//
//    @Override
//    public Collection<Session> getActiveSessions() {
//        Set<Session> sessions = new HashSet<Session>();
//        Set<String> keys = redisTemplate.keys(this.keyPrefix + "*");
//        if(keys != null && keys.size()>0){
//            for(String key:keys){
//                Session s = (Session)redisTemplate.opsForValue().get(key);
//                sessions.add(s);
//            }
//        }
//        
//        return sessions;
//    }
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = this.generateSessionId(session);  
//        this.assignSessionId(session, sessionId);
//        this.saveSession(session);
//        return sessionId;
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        if(sessionId == null){
//            logger.error("session id is null");
//            return null;
//        }
//        Session s = (Session)redisTemplate.opsForValue().get(keyPrefix+sessionId);
//        return s;
//    }
//    
//    /**
//     * Returns the Redis session keys
//     * prefix.
//     * @return The prefix
//     */
//    public String getKeyPrefix() {
//        return keyPrefix;
//    }
//
//    /**
//     * Sets the Redis sessions key 
//     * prefix.
//     * @param keyPrefix The prefix
//     */
//    public void setKeyPrefix(String keyPrefix) {
//        this.keyPrefix = keyPrefix;
//    }
//
//    public RedisTemplate<String, Object> getRedisTemplate() {
//        return redisTemplate;
//    }
//
//    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//}
