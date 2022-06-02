package com.example.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yum
 * @version 1.0
 * 秒杀信息对象，用于传递给mq
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillMessage {
    private String openId;
    private Long goodId;
}
