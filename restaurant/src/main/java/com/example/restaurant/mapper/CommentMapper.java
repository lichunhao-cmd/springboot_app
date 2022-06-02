package com.example.restaurant.mapper;

import com.example.restaurant.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

    List<Comment> listComments();

    // 写入评价
    int insertComment(Comment comment);

}
