package com.example.restaurant.service;


import com.example.restaurant.entity.GoodsSeckill;
import com.example.restaurant.mapper.SeckillGoodsMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class SeckillGoodsService implements InitializingBean {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SeckillGoodsMapper seckillGoodsMapper;


    @Override
    public void afterPropertiesSet() throws Exception {

        List<GoodsSeckill> seckillGoods = seckillGoodsMapper.seckillGoodsList();
        if (seckillGoods.isEmpty()){
            return;
        }

        // 加载秒杀商品到redis中
        seckillGoods.parallelStream().forEachOrdered(stock -> {
            redisTemplate.opsForValue().set("seckillGoods_"+stock.getGoodsId(),stock.getGoodsStock());
        });

    }


}
