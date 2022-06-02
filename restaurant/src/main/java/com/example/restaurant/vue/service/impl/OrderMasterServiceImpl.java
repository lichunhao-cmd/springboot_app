package com.example.restaurant.vue.service.impl;

import com.example.restaurant.vue.mapper.OrderMasterDao;
import com.example.restaurant.vue.service.OrderMasterService;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.modules.generator.entity.OrderMasterEntity;



@Service("orderMasterServiceVue")
public class OrderMasterServiceImpl extends ServiceImpl<OrderMasterDao, OrderMasterEntity> implements OrderMasterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderMasterEntity> page = this.page(
                new Query<OrderMasterEntity>().getPage(params),
                new QueryWrapper<OrderMasterEntity>()
        );

        return new PageUtils(page);
    }

}
