package com.taoqy.common;

import com.alibaba.fastjson.util.IOUtils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * Config token redis ,The token redis is used when webservice
 * @author taoqy
 */
@Configuration
@ConfigurationProperties(prefix = "session-redis")
public class CacheRedisConfig {


    private Sentinel sentinel;

//    private Pool pool;

//    private String password;

    @Bean("cacheRedisTemplate")
    public RedisTemplate<Object, Object> cacheRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        SerializedUtil<Object> serializedUtil = new SerializedUtil(Object.class);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setKeySerializer(new StringRedisSerializer(IOUtils.UTF8));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer(IOUtils.UTF8));

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    public JedisConnectionFactory redisConnectionFactory(){

        RedisSentinelConfiguration redisSentinelConfiguration;
        if (sentinel != null){
            String master = sentinel.getMaster();
            Set<String> nodes = sentinel.getNodes();
            if(master == null || master.isEmpty()) {
                throw new RuntimeException("The master of redis is null");
            }
            if (nodes == null || nodes.size() == 0){
                throw new RuntimeException("The "+ sentinel.getMaster() + "'s sentinel is null");
            }
            redisSentinelConfiguration = new RedisSentinelConfiguration(master, nodes);

        }else {
            redisSentinelConfiguration = new RedisSentinelConfiguration();
        }
//        if (pool == null) {
//            return new JedisConnectionFactory(redisSentinelConfiguration);
//        }
        Pool pool = sentinel.getPool();
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration);
        jedisConnectionFactory.setPoolConfig(poolConfig(pool));
        jedisConnectionFactory.setPassword(sentinel.getPassword());
        jedisConnectionFactory.setTimeout(sentinel.getTimeout());

//        if(StringUtils.isNotEmpty(password)){
//            jedisConnectionFactory.setPassword(password);
//        }
//        if(index != 0 ){
//            jedisConnectionFactory.setDatabase(index);
//        }
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    private JedisPoolConfig poolConfig(Pool pool) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(pool.getMaxIdle());
        jedisPoolConfig.setMaxTotal(pool.getMaxActive());
        jedisPoolConfig.setMaxWaitMillis(pool.getMaxWait());
        jedisPoolConfig.setTestOnBorrow(pool.isTestOnBorrow());

        return jedisPoolConfig;
    }

    public Sentinel getSentinel() {
        return sentinel;
    }

    public void setSentinel(Sentinel sentinel) {
        this.sentinel = sentinel;
    }


}
