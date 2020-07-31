package com.jingchu.springboot.redis.config;

import com.jingchu.springboot.redis.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


/**
 * @description: redisConfig 将对象序列化
 * @author: JingChu
 * @createtime :2020-07-31 11:19:16
 **/
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, User> userRedisTemplate(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> ser = new Jackson2JsonRedisSerializer<>(User.class);
        template.setDefaultSerializer(ser);
        return template;
    }

//    @Bean
//    @Primary
//    public RedisCacheManager userCacheManager(RedisTemplate<Object, User> userRedisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(userRedisTemplate);
//        cacheManager.setTransactionAware(true);
//        return cacheManager;
//    }


}