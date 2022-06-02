package com.example.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.entity.GoodsInfo;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.GoodsCategoryMapper;
import com.example.restaurant.mapper.GoodsMapper;
import com.example.restaurant.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("goods")
public class GoodsController {


    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @GetMapping("list")
    public ResponseDTO list(){
        return goodsService.listGoods();
    }


    @GetMapping("info")
    public ResponseDTO info(@RequestParam("goodsId") Integer goodsId){
        return goodsService.info(goodsId);
    }


    @PostMapping("list_limit")
    public ResponseDTO listForPage(@RequestBody String requestJson){

        System.out.println(requestJson);
        JSONObject data = JSON.parseObject(requestJson);
        Integer limit = data.getInteger("limit");
        Integer page = data.getInteger("page");
        return goodsService.listGoodsByLimit(page,limit);
    }

    @PostMapping("insert")
    public ResponseDTO insert(@RequestBody String requestJson){
        GoodsInfo goodsInfo = JSONObject.parseObject(requestJson, GoodsInfo.class);
        return goodsService.insert(goodsInfo);
    }

    @PostMapping("update")
    public ResponseDTO update(@RequestBody String requestJson){
        GoodsInfo goodsInfo = JSONObject.parseObject(requestJson, GoodsInfo.class);
        return goodsService.update(goodsInfo);
    }

    @GetMapping("delete")
    public ResponseDTO delete(@RequestParam("goodsId") Integer goodsId){
        return goodsService.delete(goodsId);
    }

    @GetMapping("category")
    public ResponseDTO category(){
        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,goodsCategoryMapper.listGoodsCategory());
    }
}
