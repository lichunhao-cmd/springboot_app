package com.example.restaurant.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.restaurant.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {


    // 请求处理器方法拦截前，进行令牌验证
    /*
    *   重定向到login 登录页面 token 验证不通过，或者为空
    *
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取 token
        String token = request.getHeader("token");
        if (JWTUtils.verifyToken(token)){
            return true;
        }
        // 返回
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "401");
        jsonObject.put("msg", "请登录！");
        jsonObject.put("obj", "");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getOutputStream().write(jsonObject.toJSONString().getBytes("UTF-8"));

        return false;

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截A");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截后");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
