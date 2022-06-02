package com.example.restaurant.entity;

import com.example.restaurant.enums.OrderStatusEnum;
import com.example.restaurant.enums.PayStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单的主表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMaster {

    //订单的id
    private String orderId;

    //买家名字
    private String buyerName;

    //买家的电话
    private String buyerPhone;

    //买家的座号
    private String buyerTableNumber;

    //买家微信Openid
    private String buyerOpenid;

    //订单总金额
    private String orderAmount;

    //订单状态，默认为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态,默认为0,未支付
    //TODO
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    // 订单备注
    private String buyerRemarks;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

}
