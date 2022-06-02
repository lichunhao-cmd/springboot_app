package com.example.restaurant.vue.service.impl;

import com.example.restaurant.vue.mapper.GoodsCategoryDao;
import com.example.restaurant.vue.service.GoodsCategoryService;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.generator.entity.GoodsCategoryEntity;



@Service("goodsCategoryServiceVue")
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryDao, GoodsCategoryEntity> implements GoodsCategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsCategoryEntity> page = this.page(
                new Query<GoodsCategoryEntity>().getPage(params),
                new QueryWrapper<GoodsCategoryEntity>()
        );

        return new PageUtils(page);
    }

}
