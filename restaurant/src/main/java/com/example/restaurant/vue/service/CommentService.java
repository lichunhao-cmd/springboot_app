package com.example.restaurant.vue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.restaurant.vue.utils.PageUtils;
import io.renren.modules.generator.entity.CommentEntity;

import java.util.Map;

/**
 * 评论信息表
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

