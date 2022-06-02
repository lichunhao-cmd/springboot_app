package com.example.restaurant.mapper;

import com.example.restaurant.entity.GoodsSeckill;

import java.util.List;

public interface SeckillGoodsMapper {

    GoodsSeckill checkSeckillGoods(Long goodsId);//校验库存
    int updateSeckillGoods(GoodsSeckill goodsSeckill);//扣除库存

    List<GoodsSeckill> seckillGoodsList();
}
