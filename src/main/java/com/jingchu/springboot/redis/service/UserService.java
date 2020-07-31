package com.jingchu.springboot.redis.service;

import com.jingchu.springboot.redis.entity.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * @description: user服务
 * @author: JingChu
 * @createtime :2020-07-31 10:19:59
 **/
public interface UserService {
    @Cacheable(cacheNames = "user",cacheManager = "userCacheManager")
    User getUserById(int id);
}
