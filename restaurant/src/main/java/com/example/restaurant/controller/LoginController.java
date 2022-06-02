package com.example.restaurant.controller;

import com.example.restaurant.dto.ResponseDTO;

import com.example.restaurant.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class LoginController {


    @Autowired
    UserService userService;

    /**
     * 微信用户登录详情
     */
    @PostMapping("wx_login")
    public ResponseDTO login(@RequestParam(value = "code", required = false) String code,
                             @RequestParam(value = "rawData", required = false) String rawData,
                             @RequestParam(value = "signature", required = false) String signature,
                             @RequestParam(value = "encrypteData", required = false) String encrypteData,
                             @RequestParam(value = "iv", required = false) String iv,
                             HttpServletResponse httpServletResponse) {

        return userService.login(code,rawData,signature,encrypteData,iv,httpServletResponse);

    }
}
