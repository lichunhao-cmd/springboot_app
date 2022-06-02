package com.example.restaurant.dto;

import com.example.restaurant.enums.GoodsStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    //
    private String orderId;

    //商品id
    private String goodsId;

    //商品名称
    private String goodsName;

    //商品价格
    private BigDecimal goodsPrice;

    //商品描述
    private String goodsDescription;

    //商品状态( 0正常 , 1下架 )
    private Integer goodsStatus;

    private Integer quantity;



}
