package com.example.restaurant.mapper;

import com.example.restaurant.dto.OrderDTO;
import com.example.restaurant.dto.OrderDetailDTO;
import com.example.restaurant.entity.OrderDetail;
import com.example.restaurant.entity.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    // 主订单 insert
    int insertOrderMaster(OrderMaster orderMaster);

    // 订单详情
    int insertOrderDetail(List<OrderDTO> orderDTOS);

    // 查询订单
    List<OrderDetailDTO> selectOrder(@Param("openId") String openId);

    // 未评价订单
    List<OrderDetailDTO> selectOrderCommentNull(@Param("openId") String openId);

    // 修改订单状态
    int updateOrderStatus(@Param("orderId")String orderId);

    List<OrderMaster> selectOrderForPage(@Param("page") Integer page,@Param("limit") Integer limit);
}
