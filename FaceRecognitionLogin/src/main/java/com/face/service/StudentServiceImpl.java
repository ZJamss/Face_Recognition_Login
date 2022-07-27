package com.face.service;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baidu.aip.face.AipFace;
import com.face.common.R;
import com.face.constant.Constant;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Program: FaceRecognitionLogin
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-07-22 20:28
 **/
@Service
public class StudentServiceImpl implements IStudentService {

    private final static AipFace CLIENT = new AipFace(Constant.Baidu.APP_ID, Constant.Baidu.API_KEY, Constant.Baidu.SECRET_KEY);

    private final static HashMap<String, String> PARAMS_MAP = new HashMap<>();

    static {
        PARAMS_MAP.put("quality_control", "NORMAL");//图片质量
        PARAMS_MAP.put("liveness_control", "LOW");//活体检测paramsMap
    }

    @Override
    public R registerFace(String url) {

        //调用api方法完成人脸注册
        /**
         * 参数1：图片的url或者base64字符串
         * 参数2：图片形式（URL，BASE64）
         * 参数3：组Id（固定一个字符串）
         * 参数4：用户Id
         * 参数5：hashMap基本参数配置
         */
        /**
         * {
         *     "result":{
         *         "face_token":"f57bb6f650bdf5b7a8354de85f4055e1",
         *         "location":{
         *             "top":80.8,
         *             "left":79.27,
         *             "rotation":0,
         *             "width":60,
         *             "height":60
         *         }
         *     },
         *     "log_id":1849552213,
         *     "error_msg":"SUCCESS",
         *     "cached":0,
         *     "error_code":0,
         *     "timestamp":1658565049
         * }
         * */
        JSONObject res = CLIENT.addUser(url, "URL", "stu", new Date().getTime()+"", PARAMS_MAP);
        return R.ok(res.getString("error_msg"), res.getInt("error_code"));
    }

    @Override
    public R login(String url) {
        //调用Api方法进行人脸检测
        /**
         * 参数1：图片的url或者base64字符串
         * 参数2：图片形式（URL，BASE64）
         * 参数3：hashMap中的基本参数配置（null:使用默认配置）
         */
        JSONObject res = CLIENT.search(url, "URL", "stu",null);
        return R.ok(res.getString("error_msg"), res.getInt("error_code")).put("data", res.toString());
    }

    public String uploadOSS(InputStream file, String fileName) {
        OSS oss = new OSSClientBuilder().build(Constant.OSS.ENDPOINT, Constant.OSS.KEY_ID, Constant.OSS.KEY_SECRET);
        oss.putObject(Constant.OSS.BUCKET, fileName, file);
        oss.shutdown();
        return "https://" + Constant.OSS.BUCKET + "." + Constant.OSS.ENDPOINT + "/" + fileName;
    }
}
