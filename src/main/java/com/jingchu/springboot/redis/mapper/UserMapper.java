package com.jingchu.springboot.redis.mapper;

import com.jingchu.springboot.redis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description: User Mapper文件
 * @author: JingChu
 * @createtime :2020-07-31 10:17:21
 **/
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id=#{id}")
    User getUserById(@Param("id") int id);
}
