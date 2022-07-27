package com.face.controller;

import com.face.common.R;
import com.face.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Program: FaceRecognitionLogin
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-07-22 20:25
 **/
@CrossOrigin(origins = "*")
@RestController
public class IndexController {

    @Autowired
    IStudentService service;

    SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

    @PostMapping("/register")
    public R register(@RequestParam("stuName") String stuName, @RequestParam("stuPhoto") MultipartFile file) throws IOException {
        String fileName = "stu/"+stuName+".jpg";
        final String url = service.uploadOSS(file.getInputStream(), fileName);
        return service.registerFace(url);
    }


    @PostMapping("/login")
    public R login(@RequestParam("stuPhoto") MultipartFile file) throws IOException {
        String fileName = "login/"+sdf.format(new Date())+".jpg";
        final String url = service.uploadOSS(file.getInputStream(), fileName);
        return service.login(url);
    }


}
