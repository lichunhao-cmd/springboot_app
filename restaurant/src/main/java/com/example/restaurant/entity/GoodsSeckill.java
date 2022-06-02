package com.example.restaurant.entity;

import com.example.restaurant.enums.GoodsStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品表
 */

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GoodsSeckill {

    //商品id
    private String goodsId;

    //商品名称
    private String goodsName;

    //商品价格
    private BigDecimal goodsPrice;

    //商品库存
    private Integer goodsStock;

    //商品图片
    private String goodsIcon;

    //商品描述
    private String goodsDescription;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;




}
