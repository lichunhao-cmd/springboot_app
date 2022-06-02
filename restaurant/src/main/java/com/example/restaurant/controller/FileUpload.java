package com.example.restaurant.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.restaurant.dto.ResponseDTO;
import com.example.restaurant.exception.ErrorConfig;
import com.example.restaurant.utils.UploadService;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@RestController
public class FileUpload {


    @PostMapping("/upload")
    public ResponseDTO uploadImage(@RequestParam("file") MultipartFile file) {
        String url = UploadService.uploadImage(file);
        return ResponseDTO.onSuccess(ErrorConfig.DO_SUCCESS,url);
    }
}
