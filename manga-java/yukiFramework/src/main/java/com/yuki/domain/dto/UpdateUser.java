package com.yuki.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {
//    username: '',
//    password: '',
//    birthday: '',
//    email: '',
//    avatar:'',
    private String username;
    private String password;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String email;
    // 这个后面要修改成文件格式，现在这里放图片的url
    private String avatar;
}
