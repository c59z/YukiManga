package com.yuki.Controller;

import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.UpdateUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userCenter")
public class UserController extends BaseController{

    @GetMapping("/info")
    public ResponseResult getUserInfo(){
        return userService.getUserInfo();
    }

    @Transactional
    @PostMapping("/update")
    public ResponseResult UpdateUser(@RequestBody UpdateUser user){
        return userService.updateUserData(user);
    }


    @PostMapping("/userAuthentication")
    public ResponseResult UserAuthentication(@RequestBody String value){
        return  userService.userAuthentication(value);
    }


}
