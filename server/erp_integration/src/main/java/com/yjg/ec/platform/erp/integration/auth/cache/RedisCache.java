package com.yjg.ec.platform.erp.integration.auth.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.io.DefaultSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis基础类
 *
 * @param <K>
 * @param <V>
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);

    private final String name;
    private RedisTemplate template;
    private final int timeout;

    private final static DefaultSerializer<String> stringDefaultSerializer = new DefaultSerializer<String>();
    private final static DefaultSerializer<Object> objectDefaultSerializer = new DefaultSerializer<Object>();

    public RedisCache(String name, RedisTemplate template, int timeout) {
        this.name = name;
        this.template = template;
        this.timeout = timeout;
    }

    @Override
    public V get(K key) throws CacheException {
        if (log.isTraceEnabled()) {
            log.trace("Getting object from cache [" + name + "] for key [" + key + "]");
        }

        V value = null;

        if (key != null) {
            template.opsForValue().get(getCacheKey(key)+"");

            /*final byte[] keyByte = stringDefaultSerializer.serialize(getCacheKey(key) + "");
            try {
                value = (V) template.execute(new RedisCallback<V>() {
                    @Override
                    public V doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        byte[] valueByte = redisConnection.get(keyByte);
                        if (valueByte == null) {
                            return null;
                        }
                        return (V) objectDefaultSerializer.deserialize(valueByte);
                    }
                });
            } catch (Throwable t) {
                throw new CacheException(t);
            }*/
        }

        return value;
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if (log.isTraceEnabled()) {
            log.trace("Putting object in cache [" + name + "] for key [" + key + "]");
        }
        try {
            if (key != null) {
                template.opsForValue().set(getCacheKey(key), value, timeout, TimeUnit.SECONDS);
                /*final byte[] keyByte = stringDefaultSerializer.serialize(getCacheKey(key) + "");
                final byte[] valueByte = objectDefaultSerializer.serialize(value);
                template.execute(new RedisCallback<Boolean>() {
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                        connection.setEx(keyByte, timeout, valueByte);
                        return true;
                    }
                });*/
            }
            return get(key);
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        if (log.isTraceEnabled()) {
            log.trace("Removing object from cache [" + name + "] for key [" + key + "]");
        }
        try {
            V previous = get(key);

            if (key != null) {
                template.delete(getCacheKey(key));

                /*final byte[] keyByte = stringDefaultSerializer.serialize(getCacheKey(key) + "");
                template.execute(new RedisCallback<Boolean>() {
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                        connection.expire(keyByte, 0);
                        return true;
                    }
                });*/
            }

            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private Object getCacheKey(K key) {
        return name + key;
    }
}
