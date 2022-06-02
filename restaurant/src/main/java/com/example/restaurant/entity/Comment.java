package com.example.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    //评论表id
    private Integer commentId;

    //评论者的名字
    private String commentName;

    //评论者的openid
    private String openId;

    //评论内容
    private String commentContent;


    //创建时间
    private Date createTime;

    // 星星
    private Integer commentStars;

    private String avatarUrl;

}
