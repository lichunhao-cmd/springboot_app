package com.example.restaurant.vue.service.impl;

import com.example.restaurant.vue.mapper.CommentDao;
import com.example.restaurant.vue.service.CommentService;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.generator.entity.CommentEntity;


@Service("commentServiceVue")
public class CommentServiceImpl extends ServiceImpl<CommentDao, CommentEntity> implements CommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommentEntity> page = this.page(
                new Query<CommentEntity>().getPage(params),
                new QueryWrapper<CommentEntity>()
        );

        return new PageUtils(page);
    }

}
