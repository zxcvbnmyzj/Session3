package com.yang.session.Config.redis;

import com.yang.session.Utis.spring.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.availability.ApplicationAvailability;
import org.springframework.boot.web.servlet.filter.ApplicationContextHeaderFilter;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//redis缓存
public class RedisCache implements Cache {

    //记录器
    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    //读写锁
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //有效时间
    private static final long EXPIRE_TIME_IN_MINUTES = 30;

    //id
    private String id = "";

    private RedisTemplate redisTemplate;

    public RedisCache(String id){
        if(id==null)
            throw new IllegalArgumentException("Cache instances require an ID");
        this.id = id;
    }

    private RedisTemplate getRedisTemplate(){
        if(redisTemplate == null)
           redisTemplate = (RedisTemplate) ApplicationContextHolder.getBean("redisTemplate");
        return redisTemplate;
    }



    @Override
    public String getId() {
        return id;
    }

    /**
     * 将内容放到redis缓存中
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key,value,EXPIRE_TIME_IN_MINUTES, TimeUnit.SECONDS);
        logger.debug("Put query result to redis");
    }

    /**
     * 从缓存中获取内容
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        logger.debug("Get cache query result from redis");
        return opsForValue.get(key);
    }


    /**
     * 从缓存中删除该内容
     * @param key
     * @return
     */
    @Override
    public Object removeObject(Object key) {
        RedisTemplate redisTEmplate = getRedisTemplate();
        redisTEmplate.delete(key);
        logger.debug("Remove cache query result from redis");
        return null;
    }


    /**
     * 清除缓存
     */
    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
        logger.debug("Clear all the cached query result from redis");
    }

    @Override
    public int getSize() {
        return 0;
    }


    @Override
    public ReadWriteLock getReadWriteLock(){
        return readWriteLock;
    }
}
