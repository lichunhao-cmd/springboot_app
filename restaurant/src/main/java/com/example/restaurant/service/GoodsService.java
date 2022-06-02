package com.example.restaurant.service;

import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.entity.GoodsCategory;
import com.example.restaurant.entity.GoodsInfo;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.GoodsMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public ResponseDTO listGoods(){
        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,goodsMapper.listGoods());
    }


    public ResponseDTO info(Integer goodsId){
        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,goodsMapper.getGoodsInfoById(goodsId));
    }


    public ResponseDTO listGoodsByLimit(Integer page ,Integer limit){

        List<GoodsInfo> goodsInfos = goodsMapper.listGoodsByLimit(page - 1, limit);
        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,goodsInfos);

    }

    public ResponseDTO insert(GoodsInfo goodsInfo){

        int i = goodsMapper.insertGoodsInfo(goodsInfo);
        if (i > 0){
            return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,i);
        }
        return ResponseDTO.onError(ErrorConfig.IS_ERROR);
    }

    public ResponseDTO update(GoodsInfo goodsInfo){
        int i = goodsMapper.updateGoodsInfo(goodsInfo);
        if (i>0){
            return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,i);
        }
        return ResponseDTO.onError(ErrorConfig.IS_ERROR);
    }
    public ResponseDTO delete(Integer goodsId){

        int i = goodsMapper.deleteGoodsInfo(goodsId);
        if (i>0){
            return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,i);
        }
        return ResponseDTO.onError(ErrorConfig.IS_ERROR);
    }
}
