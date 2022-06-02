package com.example.restaurant.mapper;

import com.example.restaurant.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper{

    // select
    User selectUserById(String openId);

    //insert
    int insertUser(User user);

    //update
    int updateUserById(User user);

    // 修改桌号
    int updateUserForTable(@Param("tableNumber") Integer tableNumber,@Param("openId") String openId);

    // 设null
    int updateUserForNull(@Param("openId") String openId);

}
