package com.example.restaurant.controller;

import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("list")
    public ResponseDTO list(){
        return commentService.list();
    }

    @PostMapping("create")
    public ResponseDTO create(@RequestBody String requestJson,
                              HttpServletRequest request) throws InterruptedException {

        System.out.println(requestJson);

        return commentService.create(requestJson,request);
    }
}
