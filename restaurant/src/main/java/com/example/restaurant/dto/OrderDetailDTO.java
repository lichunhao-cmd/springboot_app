package com.example.restaurant.dto;

import com.example.restaurant.entity.OrderDetail;
import com.example.restaurant.enums.OrderStatusEnum;
import com.example.restaurant.enums.PayStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    //订单的id
    private String orderId;

    //买家名字
    private String buyerName;

    //买家的座号
    private String buyerTableNumber;

    //订单总金额
    private String orderAmount;

    //支付状态,默认为0,未支付
    //TODO
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    //备注
    private String buyerRemarks;

    //创建时间
    private Date createTime;

    // 详情
    private List<OrderDetail> orderDetails;


}
