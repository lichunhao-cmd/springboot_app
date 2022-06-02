package io.renren.modules.generator.service.impl;

import com.example.restaurant.vue.mapper.GoodsInfoDao;

import com.example.restaurant.vue.service.GoodsInfoServiceVue;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.generator.entity.GoodsInfoEntity;



@Service("goodsInfoServiceVue")
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoDao, GoodsInfoEntity> implements GoodsInfoServiceVue {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        IPage<GoodsInfoEntity> page = this.page(
                new Query<GoodsInfoEntity>().getPage(params),
                new QueryWrapper<GoodsInfoEntity>()
        );
        return new PageUtils(page);
    }

}
