package com.example.restaurant.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件上传
 */

public class UploadService {
    //支持的图片类型
    private static final List<String> CONTENT_TYPE = Arrays.asList("image/gif", "image/jpeg","image/png");
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);


    public static String uploadImage(MultipartFile file) {
        String imgName = file.getOriginalFilename();
        //建议文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPE.contains(contentType)) {
            LOGGER.info("文件类型不合法:{}", imgName);
            return null;
        }
        try {
            //检验文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                LOGGER.info("文件内容不合法:{}", imgName);
                return null;
            }
            //保存文件
            File file1 = new File("src/main/resources/static/goodsImg/");
            String canonicalPath = file1.getCanonicalPath();
            file.transferTo(new File(canonicalPath+"/"+imgName));
        } catch (IOException e) {
            LOGGER.error("服务器内部错误->:{}", imgName);
            e.printStackTrace();
        }//返回图片路径
        return "/" + imgName;
    }
}
