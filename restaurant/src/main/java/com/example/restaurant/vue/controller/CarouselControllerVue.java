package com.example.restaurant.vue.controller;

import java.util.Arrays;
import java.util.Map;


import com.example.restaurant.vue.service.CarouselService;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.CarouselEntity;



/**
 *
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@RestController
@RequestMapping("generator/carousel")
public class CarouselControllerVue {
    @Autowired
    private CarouselService carouselService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:carousel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = carouselService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carouselId}")
    @RequiresPermissions("generator:carousel:info")
    public R info(@PathVariable("carouselId") Integer carouselId){
		CarouselEntity carousel = carouselService.getById(carouselId);

        return R.ok().put("carousel", carousel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:carousel:save")
    public R save(@RequestBody CarouselEntity carousel){
		carouselService.save(carousel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:carousel:update")
    public R update(@RequestBody CarouselEntity carousel){
		carouselService.updateById(carousel);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:carousel:delete")
    public R delete(@RequestBody Integer[] carouselIds){
		carouselService.removeByIds(Arrays.asList(carouselIds));

        return R.ok();
    }

}
