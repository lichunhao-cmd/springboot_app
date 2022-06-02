package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论信息表
 * 
 * @author chunhao
 * @email sunlightcs@gmail.com
 * @date 2022-05-09 22:19:29
 */
@Data
@TableName("comment")
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 评论信息的id
	 */
	@TableId
	private Integer commentId;
	/**
	 * 主订单id
	 */
	private String orderId;
	/**
	 * 评论者姓名
	 */
	private String commentName;
	/**
	 * 评论者的openid
	 */
	private String openid;
	/**
	 * 几个星星
	 */
	private Integer commentStars;
	/**
	 * 评论内容
	 */
	private String commentContent;
	/**
	 * 评论时间
	 */
	private Date createTime;

}
