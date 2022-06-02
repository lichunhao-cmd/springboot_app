package com.example.restaurant.vue.controller;

import java.util.Arrays;
import java.util.Map;


import com.example.restaurant.vue.service.GoodsInfoServiceVue;
import com.example.restaurant.vue.utils.PageUtils;
import com.example.restaurant.vue.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.GoodsInfoEntity;




/**
 * 商品表
 *
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@RestController
@RequestMapping("generator/goodsinfo")
public class GoodsInfoControllerVue {

    @Autowired(required = false)
    private GoodsInfoServiceVue goodsInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{goodsId}")
    @RequiresPermissions("generator:goodsinfo:info")
    public R info(@PathVariable("goodsId") String goodsId){
		GoodsInfoEntity goodsInfo = goodsInfoService.getById(goodsId);

        return R.ok().put("goodsInfo", goodsInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:goodsinfo:save")
    public R save(@RequestBody GoodsInfoEntity goodsInfo){
		goodsInfoService.save(goodsInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:goodsinfo:update")
    public R update(@RequestBody GoodsInfoEntity goodsInfo){
		goodsInfoService.updateById(goodsInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:goodsinfo:delete")
    public R delete(@RequestBody String[] goodsIds){
		goodsInfoService.removeByIds(Arrays.asList(goodsIds));

        return R.ok();
    }

}
