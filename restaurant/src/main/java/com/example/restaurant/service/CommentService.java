package com.example.restaurant.service;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.entity.Comment;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.mapper.CommentMapper;
import com.example.restaurant.mapper.OrderMapper;
import com.example.restaurant.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.x509.RDN;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RedisTemplate redisTemplate;

    public ResponseDTO list(){

        if (redisTemplate.hasKey("comment")){
            List<Comment> comments = (List<Comment>) redisTemplate.opsForValue().get("comment");
            return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,comments);
        }
        List<Comment> comments = commentMapper.listComments();
        redisTemplate.opsForValue().set("comment",comments);

        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,comments);
    }


    @Transactional
    public ResponseDTO create(String requestJson, HttpServletRequest request) throws InterruptedException {

        String token = request.getHeader("token");
        String openId = JWTUtils.getInfo(token);

        JSONObject data = JSON.parseObject(requestJson);

        int orderId = orderMapper.updateOrderStatus(data.getString("orderId"));
        if (orderId > 0){
            Comment comment = new Comment();
            comment.setCommentStars(data.getInteger("commentStars"));
            comment.setOpenId(openId);
            comment.setCommentContent(data.getString("commentContent"));

            Set<String> keys = redisTemplate.keys("*");
            redisTemplate.delete(keys);
            int i = commentMapper.insertComment(comment);
            //Thread.sleep(0.1);
            redisTemplate.delete(keys);

            if (i > 0){
                return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,i);
            }
        }

        return ResponseDTO.onError(ErrorConfig.IS_ERROR);
    }

}
