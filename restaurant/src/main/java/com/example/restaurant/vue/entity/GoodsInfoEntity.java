package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品表
 * 
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@Data
@TableName("goods_info")
public class GoodsInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	@TableId
	private String goodsId;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * 商品单价
	 */
	private BigDecimal goodsPrice;
	/**
	 * 库存
	 */
	private Integer goodsStock;
	/**
	 * 商品图片
	 */
	private String goodsIcon;
	/**
	 * 商品描述
	 */
	private String goodsDescription;
	/**
	 * 商品状态 , 0正常 , 1下架
	 */
	private Integer goodsStatus;
	/**
	 * 类目编号
	 */
	private Integer categoryType;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
