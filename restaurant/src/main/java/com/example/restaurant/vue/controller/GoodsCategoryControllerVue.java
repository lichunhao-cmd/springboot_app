package com.example.restaurant.vue.controller;

import java.util.Arrays;
import java.util.Map;

import com.example.restaurant.vue.service.GoodsCategoryService;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.GoodsCategoryEntity;




/**
 * 类目表
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@RestController
@RequestMapping("generator/goodscategory")
public class GoodsCategoryControllerVue {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:goodscategory:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsCategoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{categoryId}")
    @RequiresPermissions("generator:goodscategory:info")
    public R info(@PathVariable("categoryId") Integer categoryId){
		GoodsCategoryEntity goodsCategory = goodsCategoryService.getById(categoryId);

        return R.ok().put("goodsCategory", goodsCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:goodscategory:save")
    public R save(@RequestBody GoodsCategoryEntity goodsCategory){
		goodsCategoryService.save(goodsCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:goodscategory:update")
    public R update(@RequestBody GoodsCategoryEntity goodsCategory){
		goodsCategoryService.updateById(goodsCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:goodscategory:delete")
    public R delete(@RequestBody Integer[] categoryIds){
		goodsCategoryService.removeByIds(Arrays.asList(categoryIds));

        return R.ok();
    }

}
