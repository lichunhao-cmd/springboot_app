package com.example.restaurant.config;

import com.example.restaurant.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptor = registry.addInterceptor(new JWTInterceptor());
        interceptor.excludePathPatterns("/user/wx_login")
                .excludePathPatterns("/goodsImg/**")
                .excludePathPatterns("/images/**")
//                .addPathPatterns("/order/**")
                .addPathPatterns("/tables/create","/tables/delete");
        //interceptor.addPathPatterns("/**"); 默认全拦截action
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/goodsImg/**");
////                .addResourceLocations("file:" +System.getProperty("user.dir")+"\\src\\main\\resources\\static\\goodsImg\\");
//    }

}
