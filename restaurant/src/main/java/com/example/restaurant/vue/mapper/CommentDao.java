package com.example.restaurant.vue.mapper;

import io.renren.modules.generator.entity.CommentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论信息表
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@Mapper
public interface CommentDao extends BaseMapper<CommentEntity> {

}
