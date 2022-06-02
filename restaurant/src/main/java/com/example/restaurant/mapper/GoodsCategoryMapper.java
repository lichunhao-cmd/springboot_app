package com.example.restaurant.mapper;

import com.example.restaurant.entity.GoodsCategory;

import java.util.List;

public interface GoodsCategoryMapper {

    /**
     *通过 类目编号 查询类目
     */
    List<GoodsCategory> listGoodsCategory();

}
