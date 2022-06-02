package com.example.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.restaurant.dto.OrderFormDTO;
import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    // 创建订单
    @PostMapping("create")
    public ResponseDTO create(@RequestBody String requestJson, HttpServletRequest request){


        System.out.println(requestJson);
        OrderFormDTO orderFormDTO = JSONObject.parseObject(requestJson, OrderFormDTO.class);

        System.out.println(orderFormDTO.getOrderDTOS());

        return orderService.create(orderFormDTO,request);
    }

    // 所有订单
    @GetMapping("list")
    public ResponseDTO select(HttpServletRequest request){
        return orderService.selectOrder(request);
    }

    // 未评价订单
    @GetMapping("comment_null")
    public ResponseDTO selectComment(HttpServletRequest request){


        return orderService.selectOrderNull(request);
    }

    @PostMapping("list_limit")
    public ResponseDTO listForPage(@RequestBody String requestJson){

        System.out.println(requestJson);
        JSONObject data = JSON.parseObject(requestJson);
        Integer limit = data.getInteger("limit");
        Integer page = data.getInteger("page");

        return orderService.selectOrderForPage(page,limit);
    }
}
