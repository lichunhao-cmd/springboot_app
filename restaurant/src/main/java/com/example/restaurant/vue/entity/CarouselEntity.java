package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@Data
@TableName("carousel")
public class CarouselEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 首页轮播图主键id
	 */
	@TableId
	private Integer carouselId;
	/**
	 * 轮播图
	 */
	private String carouselUrl;
	/**
	 * 点击后的跳转地址(默认不跳转)
	 */
	private String redirectUrl;
	/**
	 * 排序值(字段越大越靠前)
	 */
	private Integer carouselRank;
	/**
	 * 删除标识字段(0-未删除 1-已删除)
	 */
	private Integer isDeleted;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建者id
	 */
	private Integer createUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改者id
	 */
	private Integer updateUser;

}
