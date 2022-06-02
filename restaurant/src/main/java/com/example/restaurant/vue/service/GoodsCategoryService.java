package com.example.restaurant.vue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.restaurant.vue.utils.PageUtils;

import io.renren.modules.generator.entity.GoodsCategoryEntity;

import java.util.Map;

/**
 * 类目表
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
public interface GoodsCategoryService extends IService<GoodsCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

