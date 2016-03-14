package com.core.mapper;

import com.core.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by stereomatrix on 2016/1/20.
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    @Insert("insert into user(id, name, phone, balance) values(${user.id}, ${user.name}, ${user.phone}, ${user.balance})")
    void addUser(@Param(value = "user") User user);
}
