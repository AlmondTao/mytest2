package com.taoqy.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2020/1/13
 * @see [相关类/方法]
 * @since bapfopm-pfpsmas-cbfsms-service 1.0
 */
@Component
public class CacheRedisUtils {

    private final Logger log =  LoggerFactory.getLogger(CacheRedisUtils.class);

    @Autowired
    @Qualifier("cacheRedisTemplate")
    private RedisTemplate cacheRedisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<>();


    public static final String UNLOCK_LUA;

    static{
        StringBuilder sb=new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append(" return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append(" return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();

    }

    public boolean lock(String key,long expire,int retryTimes,long sleepMillis){
        boolean result = setLock(key, expire);
        while((!result) && retryTimes -- >0){
            try {
                log.warn("重新获得锁");
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result =setLock(key, expire);
        }
        return result;
    }

    private boolean setLock(String key,long expire){

        // 使用Lua脚本删除Redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
// spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
        String result = (String) cacheRedisTemplate.execute(new RedisCallback<String>(){
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                String uuid = KeyGreaterUtil2.greater('J');
                lockFlag.set(uuid);
                //EX为秒 px为毫秒
                return ((JedisCommands)nativeConnection).set(key, uuid, "nx", "EX", expire);

            }
        });
        return !StringUtils.isEmpty(result);
    }

    public boolean releaseLock(String key){
        List<String> keys = new ArrayList<>();
        keys.add(key);
        List<String> args = new ArrayList<>();
        args.add(lockFlag.get());


        Long result = (Long) cacheRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                Object nativeConnection = connection.getNativeConnection();
                if(nativeConnection instanceof JedisCluster){
                    return (Long) ((JedisCluster)nativeConnection).eval(UNLOCK_LUA,keys,args );
                }

                else if (nativeConnection instanceof Jedis){
                    return (Long)((Jedis)nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            }
        });
        return true;
    }
}
