package com.example.restaurant.controller.seckillApi;

import com.alibaba.fastjson.JSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
*  @author lichunhao
* */
@Controller
@RestController
public class SeckillGoodsController {

    private static ConcurrentHashMap<Long,Boolean> sign = new ConcurrentHashMap<>();


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    MQSender mqSender;

    @RequestMapping("/seckill")
    public String seckill(Long goodsId, HttpServletRequest request){

//        String token = request.getHeader("token");
//        String info = JWTUtils.getInfo(token);
        if (sign.get(goodsId) != null){
            return "卖完";
        }

        Long decrement = redisTemplate.opsForValue().decrement("seckillGoods_" + goodsId);
        if (decrement < 0){

            sign.put(goodsId,true);
            // 防止少卖
            redisTemplate.opsForValue().increment("seckillGoods_" + goodsId);

            return "null";
        }

        // 订单操作
        try{
            //根据商品id创建订单,返回创建订单的id
            SeckillMessage seckillMessage = new SeckillMessage("ss",goodsId);
            mqSender.sendSeckillMessage(JSON.toJSONString(seckillMessage));
            return "已下单";

        }catch (Exception e){
            // 数据库 创建订单失败，还原库存
            redisTemplate.opsForValue().increment("seckillGoods_" + goodsId);
            if (sign.get(goodsId) != null){
                sign.remove(goodsId);
            }
            e.printStackTrace();
            return e.getMessage();
        }
    }

}


