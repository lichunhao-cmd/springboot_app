package com.example.restaurant.enums;

import lombok.Getter;

/**
 * 商品状态的枚举类
 */
@Getter
public enum GoodsStatusEnum implements CodeEnum {
    UP(0 , "在售") ,
    DOWN(1 , "下架")
    ;

    private Integer code;

    private String message;

    GoodsStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
