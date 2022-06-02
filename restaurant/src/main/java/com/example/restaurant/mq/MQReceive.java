package com.example.restaurant.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.restaurant.dto.OrderDTO;
import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.entity.GoodsSeckill;
import com.example.restaurant.entity.OrderMaster;
import com.example.restaurant.entity.SeckillMessage;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.mapper.SeckillGoodsMapper;
import com.example.restaurant.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 消息接收者
 */
@Service
@Slf4j
@Transactional
public class MQReceive {

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 接收消息，做下单处理
     * @param message
     */
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message) {

        SeckillMessage seckillMessage = JSONObject.parseObject(message, SeckillMessage.class);

        Long goodId = seckillMessage.getGoodId();
        String openId = seckillMessage.getOpenId();

        GoodsSeckill goodsSeckill = seckillGoodsMapper.checkSeckillGoods(goodId);
        if (goodsSeckill.getGoodsStock()<0){
            throw new RuntimeException("没库存了");
        }
        // 写入订单
        System.out.println("写入成功");


        int i = seckillGoodsMapper.updateSeckillGoods(goodsSeckill);
        if (i<=0){
            throw new RuntimeException("商品没库存");
        }


    }

}
