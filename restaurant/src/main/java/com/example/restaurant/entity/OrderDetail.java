package com.example.restaurant.entity;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 订单详情表
 */

@Data
public class OrderDetail {

    //订单详情表id
    private Integer detailId;

    //订单id
    private String orderId;

    //商品id
    private String goodsId;

    //商品名称
    private String goodsName;

    //商品单价
    private BigDecimal goodsPrice;

    //商品数量
    private Integer goodsQuantity;

    //
    private String goodsIcon;
}
