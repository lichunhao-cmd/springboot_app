package com.example.restaurant.mapper;

import com.example.restaurant.entity.GoodsCategory;
import com.example.restaurant.entity.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    // 商品数据和分类
    List<GoodsCategory> listGoods();

    // id查找
    GoodsInfo getGoodsInfoById(@Param("goodsId") Integer goodsId);

    List<GoodsInfo> listGoodsByLimit(@Param("page") Integer page,@Param("limit") Integer limit);

    List<GoodsInfo> selectGoodsForPage();

    int insertGoodsInfo(GoodsInfo goodsInfo);

    int updateGoodsInfo(GoodsInfo goodsInfo);

    int deleteGoodsInfo(@Param("goodsId") Integer goodsId);
}
