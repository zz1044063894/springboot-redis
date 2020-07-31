package com.jingchu.springboot.redis.entity;

import java.io.Serializable;

/**
 * @description: User实体类
 * @author: JingChu
 * @createtime :2020-07-31 10:12:35
 **/

public class User implements Serializable {
    private int id;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;
    private int age;
}
