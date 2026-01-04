package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.ALiOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private ALiOSSUtils aliOSSUtils;

    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//
//        log.info("上传文件：{}{}{}", username, age, image);
//        String url = aliOSSUtils.upload(image);
//
//        return Result.success(url);
//    }
    public Result upload(MultipartFile image) throws Exception {

        log.info("上传文件：{}", image);
        String url = aliOSSUtils.upload(image);

        return Result.success(url);
    }

}
