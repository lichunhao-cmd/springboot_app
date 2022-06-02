package com.example.restaurant.service;

import com.example.restaurant.dto.OrderDTO;
import com.example.restaurant.dto.OrderDetailDTO;
import com.example.restaurant.dto.OrderFormDTO;
import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.entity.OrderMaster;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.utils.JWTUtils;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;



    // 座号 订单总金额
    @Transactional
    public ResponseDTO create(OrderFormDTO orderFormDTO, HttpServletRequest request){

        String token = request.getHeader("token");
        String openId = JWTUtils.getInfo(token);

        // 生成主订单 (拦截器验证用户信息)
        String orderId = "q" + new Date().getTime();
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setBuyerOpenid(openId);
        orderMaster.setBuyerTableNumber(orderFormDTO.getTableNumber());
        orderMaster.setBuyerRemarks(orderFormDTO.getBuyerRemarks());
        orderMaster.setOrderAmount(orderFormDTO.getAllGoodsPrice());
        orderMaster.setOrderStatus(0); // 默认0
        orderMaster.setPayStatus(0); // 默认0
        int i = orderMapper.insertOrderMaster(orderMaster);
        if (i > 0){
            List<OrderDTO> orderDTOS = orderFormDTO.getOrderDTOS();
            for (OrderDTO orderDTO : orderDTOS) {
                orderDTO.setOrderId(orderId);
            }

            int i1 = orderMapper.insertOrderDetail(orderDTOS);
            if (i1 > 0){
                return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,i1);
            }
        }

        return ResponseDTO.onError(ErrorConfig.IS_ERROR);
    }

    // 详情
    public ResponseDTO selectOrder(HttpServletRequest request){

        String token = request.getHeader("token");
        String openId = JWTUtils.getInfo(token);

        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,orderMapper.selectOrder(openId));
    }

    // 未评价订单
    public ResponseDTO selectOrderNull(HttpServletRequest request){

        String token = request.getHeader("token");
        String openId = JWTUtils.getInfo(token);
        List<OrderDetailDTO> orderDetailDTOS = orderMapper.selectOrderCommentNull(openId);
        if (!orderDetailDTOS.isEmpty()){
            return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,orderDetailDTOS);
        }
        return ResponseDTO.onError(ErrorConfig.DATA_NOT_FIND);

    }

    public ResponseDTO selectOrderForPage(Integer page,Integer limit){

        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,orderMapper.selectOrderForPage(page,limit));
    }
}
