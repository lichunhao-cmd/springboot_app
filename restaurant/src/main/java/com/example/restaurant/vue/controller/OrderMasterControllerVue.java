package com.example.restaurant.vue.controller;

import java.util.Arrays;
import java.util.Map;


import com.example.restaurant.vue.service.OrderMasterService;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.OrderMasterEntity;



/**
 * 订单表
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@RestController
@RequestMapping("generator/ordermaster")
public class OrderMasterControllerVue {
    @Autowired
    private OrderMasterService orderMasterService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:ordermaster:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderMasterService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderId}")
    @RequiresPermissions("generator:ordermaster:info")
    public R info(@PathVariable("orderId") String orderId){
		OrderMasterEntity orderMaster = orderMasterService.getById(orderId);

        return R.ok().put("orderMaster", orderMaster);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:ordermaster:save")
    public R save(@RequestBody OrderMasterEntity orderMaster){
		orderMasterService.save(orderMaster);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:ordermaster:update")
    public R update(@RequestBody OrderMasterEntity orderMaster){
		orderMasterService.updateById(orderMaster);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:ordermaster:delete")
    public R delete(@RequestBody String[] orderIds){
		orderMasterService.removeByIds(Arrays.asList(orderIds));

        return R.ok();
    }

}
