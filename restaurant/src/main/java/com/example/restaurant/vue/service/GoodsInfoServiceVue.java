package com.example.restaurant.vue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.restaurant.vue.utils.PageUtils;
import io.renren.modules.generator.entity.GoodsInfoEntity;

import java.util.Map;

/**
 * 商品表
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
public interface GoodsInfoServiceVue extends IService<GoodsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

