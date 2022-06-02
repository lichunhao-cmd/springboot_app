package com.example.restaurant.vue.service.impl;

import com.example.restaurant.vue.mapper.CarouselDao;
import com.example.restaurant.vue.service.CarouselService;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.generator.entity.CarouselEntity;



@Service("carouselServiceVue")
public class CarouselServiceImpl extends ServiceImpl<CarouselDao, CarouselEntity> implements CarouselService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CarouselEntity> page = this.page(
                new Query<CarouselEntity>().getPage(params),
                new QueryWrapper<CarouselEntity>()
        );

        return new PageUtils(page);
    }

}
