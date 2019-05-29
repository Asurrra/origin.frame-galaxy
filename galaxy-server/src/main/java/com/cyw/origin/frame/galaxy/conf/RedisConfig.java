package com.cyw.origin.frame.galaxy.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@Configuration
public class RedisConfig {

    @Primary
    @Bean("redisTemplate")
    public RedisTemplate getRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        template.setDefaultSerializer(genericJackson2JsonRedisSerializer);
        return template;
    }
}
