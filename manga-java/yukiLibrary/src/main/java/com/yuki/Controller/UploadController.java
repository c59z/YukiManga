package com.yuki.Controller;

import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.UploadFile;
import com.yuki.service.SysBookinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UploadController extends BaseController {

    @Autowired
    SysBookinfoService bookinfoService;

//    @PostMapping("/test/upload")
//    public ResponseResult upload_test(UploadFile uploadFile){
//        return bookinfoService.uploadFile(uploadFile);
//    }

}
