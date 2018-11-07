package com.cyw.origin.frame.galaxy.conf;
//
//import com.weimob.saas.promotion.common.cache.redis.JedisBuilder;
//import com.weimob.saas.promotion.common.cache.redis.RedisClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * Created by god on 2017/9/11.
// */
//@Configuration
//public class RedisConf {
//
//    private static final int DEFAUL_TTIMEOUT = 1000;
//
//    @Value("${redis.maxIdle}")
//    private int maxIdle;
//    @Value("${redis.maxTotal}")
//    private int maxTotal;
//    @Value("${redis.maxWaitMillis}")
//    private int maxWaitMillis;
//
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private int port;
//    @Value("${redis.password}")
//    private String password;
//
//    @Bean
//    public RedisClient getRedisClient() {
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxTotal(maxTotal);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        jedisPoolConfig.setTestOnBorrow(true);
//
//        JedisPool jedisPool = new JedisBuilder().create(jedisPoolConfig, host, port, DEFAUL_TTIMEOUT, password);
//
//        RedisClient redisClient = new RedisClient();
//        redisClient.setJedisPool(jedisPool);
//        return redisClient;
//    }
//
//}
