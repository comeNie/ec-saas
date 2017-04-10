package com.yjg.ec.platform.erp.integration.auth.session;

import org.apache.shiro.io.DefaultSerializer;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 *shiro的session实现
 */
public class RedisShiroSessionRepository implements ShiroSessionRepository {

    private static Logger logger = LoggerFactory.getLogger(RedisShiroSessionRepository.class);

    private final static DefaultSerializer<String> stringDefaultSerializer = new DefaultSerializer<String>();
    private final static DefaultSerializer<Object> objectDefaultSerializer = new DefaultSerializer<Object>();

    private final String REDIS_SHIRO_SESSION = "cgmg_erp_shiro_session:";

    private RedisTemplate redisTemplate;

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("saveSession中session 或者 session_id为空");
            return;
        }

        redisTemplate.opsForValue().set(getRedisSessionKey(session.getId()), session, session.getTimeout(), TimeUnit.MILLISECONDS);

        /*final byte[] key = stringDefaultSerializer.serialize(getRedisSessionKey(session.getId()));
        final byte[] value = objectDefaultSerializer.serialize(session);
        try {
            final Long timeout = session.getTimeout() / 1000;
            logger.info("存储key：{}的session", getRedisSessionKey(session.getId()));
            redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.setEx(key, timeout, value);
                    return true;
                }
            });
        } catch (Exception e) {
            logger.error("save session exception", e);
        }*/
    }

    @Override
    public void deleteSession(Serializable id) {
        if (id == null) {
            logger.error("deleteSession中id为空");
            return;
        }
        try {
            redisTemplate.delete(getRedisSessionKey(id));


            /*final byte[] key = stringDefaultSerializer.serialize(getRedisSessionKey(id));
            logger.info("删除key：{}的session", getRedisSessionKey(id));
            redisTemplate.execute(new RedisCallback<Boolean>() {
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.expire(key, 0);
                    return true;
                }
            });*/
        } catch (Exception e) {
            logger.error("delete session exception", e);
        }
    }

    @Override
    public Session getSession(Serializable id) {
        if (id == null) {
            logger.error("getSession id为空");
            return null;
        }
        Session session = null;
        try {
            session = (Session) redisTemplate.opsForValue().get(getRedisSessionKey(id));

            /*final byte[] keyByte = stringDefaultSerializer.serialize(getRedisSessionKey(id));
            logger.info("获取key：{}的session", getRedisSessionKey(id));
            session = (Session) redisTemplate.execute(new RedisCallback<Session>() {
                @Override
                public Session doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    byte[] valueByte = redisConnection.get(keyByte);
                    if (valueByte == null) {
                        return null;
                    }
                    return (Session) objectDefaultSerializer.deserialize(valueByte);
                }
            });*/
        } catch (Exception e) {
            logger.error("get session exception", e);
        }
        return session;
    }

    @Override
    public Collection<Session> getAllSessions() {
        return new HashSet<Session>();
    }

    private String getRedisSessionKey(Serializable sessionId) {
        return this.REDIS_SHIRO_SESSION + sessionId;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
