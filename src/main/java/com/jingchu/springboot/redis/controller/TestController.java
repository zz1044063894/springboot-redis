package com.jingchu.springboot.redis.controller;

import com.jingchu.springboot.redis.entity.User;
import com.jingchu.springboot.redis.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 测试controller
 * @author: JingChu
 * @createtime :2020-07-31 10:10:40
 **/
@RestController
@EnableAutoConfiguration
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTemplate<Object,User> userRedisTemplate;

    @GetMapping("/redis/oprate")
    @ResponseBody
    public void text1() {
        stringRedisTemplate.opsForValue().append("msg", "hello");

        //读取数据
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
        //list存储数据　
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
        stringRedisTemplate.opsForList().leftPush("mylist", "3");
        String mylist = stringRedisTemplate.opsForList().leftPop("mylist"); //删除并查询最顶层的list数据
        System.out.println(mylist);

    }

    /**
     * 测试保存对象
     */
    @GetMapping("/redis/save/user")
    public void test2() {
        User user = userService.getUserById(1);
        System.out.println(user);
        //默认保存对象需要使用jdk序列化然后保存到redis中
        redisTemplate.opsForValue().set("user-01", user);

        //要保存json数据进redis有两种做法
        //1。自己将对象转换为JSON字符串
        //2.自定义个redisTemplate序列化规则
        userRedisTemplate.opsForValue().set("user-01", user);
    }

    @GetMapping("/get/mysql")
    @ResponseBody
    public String text(@RequestParam("id") int id) {
        User user = userService.getUserById(id);
        System.out.println(user);
        return "";
    }
}
