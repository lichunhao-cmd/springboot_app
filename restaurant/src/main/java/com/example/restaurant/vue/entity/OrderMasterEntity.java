package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * 
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@Data
@TableName("order_master")
public class OrderMasterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	@TableId
	private String orderId;
	/**
	 * 买家名称
	 */
	private String buyerName;
	/**
	 * 买家电话
	 */
	private String buyerPhone;
	/**
	 * 座号
	 */
	private String buyerTableNumber;
	/**
	 * 买家微信的openid
	 */
	private String buyerOpenid;
	/**
	 * 订单总金额
	 */
	private BigDecimal orderAmount;
	/**
	 * 订单状态 , 默认0为新订单
	 */
	private Integer orderStatus;
	/**
	 * 支付状态 , 默认0为未支付
	 */
	private Integer payStatus;
	/**
	 * 用户备注
	 */
	private String buyerRemarks;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

}
