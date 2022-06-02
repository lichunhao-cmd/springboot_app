package com.example.restaurant.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SING = "!@#$!@#";

    // 生成token  这里使用默认的header 头部信息
    public static String getToken(Map<String, String> map) {
        // 设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);
        //创建jwt
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        // 指定过期时间，生成token
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));

        return token;
    }

    // 验证是否正确（不定位错误）
    public static boolean verifyToken(String token){
        // 判断是否为空
        if (token == null){
            return false;
        }
        // 验证出错
        try {
            JWTVerifier build = JWT.require(Algorithm.HMAC256(SING)).build();
            build.verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // 获取信息
    public static String getInfo(String token){

        DecodedJWT decode = JWT.decode(token);
        String openId = decode.getClaim("user").asString();

        return openId;
    }
}
