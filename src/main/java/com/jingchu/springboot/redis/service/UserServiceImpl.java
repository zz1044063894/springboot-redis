package com.jingchu.springboot.redis.service;

import com.jingchu.springboot.redis.entity.User;
import com.jingchu.springboot.redis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description: user接口
 * @author: JingChu
 * @createtime :2020-07-31 10:20:26
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User getUserById(int id) {
        String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            User user = operations.get(key);
            System.out.println("从缓存中获得数据：" + user.getName());
            System.out.println("------------------------------------");
            return user;
        } else {
            User user = userMapper.getUserById(id);
            System.out.println("查询数据库获得数据：" + user.getName());
            System.out.println("------------------------------------");

            // 写入缓存
            operations.set(key, user, 5, TimeUnit.HOURS);

            return user;
        }
    }
}
