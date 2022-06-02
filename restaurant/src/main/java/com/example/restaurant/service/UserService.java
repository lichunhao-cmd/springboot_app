package com.example.restaurant.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.entity.User;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.UserMapper;
import com.example.restaurant.utils.JWTUtils;
import com.example.restaurant.utils.WechatUtil;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    // 登录
    public ResponseDTO login(String code, String rawData, String signature, String encrypteData, String iv
                            , HttpServletResponse httpServletResponse) {

        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject OpenId = WechatUtil.openId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = OpenId.getString("openid");
        String sessionKey = OpenId.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String s2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(s2)) {
            return ResponseDTO.onError("签名校验失败");
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        User user = this.userMapper.selectUserById(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        // 生成jwt 存入openId
        Map<String, String> map = new HashMap<>();
        map.put("user",openid);
        String token = JWTUtils.getToken(map);
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");

            user = new User();
            user.setOpenId(openid);
            user.setSkey(skey);
            user.setCreateTime(new Date());
            user.setLastVisitTime(new Date());
            user.setSessionKey(sessionKey);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarUrl(avatarUrl);
            user.setGender(Integer.parseInt(gender));
            user.setNickName(nickName);

            this.userMapper.insertUser(user);
        } else {
            // 已存在，更新用户登录时间
            user.setLastVisitTime(new Date());
            // 重新设置会话skey
            //user.setSkey(skey);
            this.userMapper.updateUserById(user);
        }
        //6. 把新的skey返回给小程序
        ResponseDTO result = ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS, token);
        System.out.println(skey);
        return result;
    }
}
