package com.face.service;

import com.face.common.R;

import java.io.InputStream;

public interface IStudentService {

    R registerFace(String url);


    R login(String url);

    public String uploadOSS(InputStream file, String fileName);

}
