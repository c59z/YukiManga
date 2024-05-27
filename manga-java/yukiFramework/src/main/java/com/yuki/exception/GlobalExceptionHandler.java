package com.yuki.exception;

import com.yuki.domain.ResponseResult;
import com.yuki.enums.AppHttpCodeEnum;
import com.yuki.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
//@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常信息
        System.out.println("发生了异常："+e.getMsg());
        //从异常对象中获取提示信息封装返回
        return ResponseResult.fail(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public String unKnowexceptionHandler(Exception e){
        //打印异常信息
        System.out.println("发生了其他异常："+e);
        System.out.println("未知异常");
        //从异常对象中获取提示信息封装返回
        return "未知异常";
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseResult exceptionHandler(Exception e){
        //打印异常信息
        System.out.println("发生了其他异常："+e);
        System.out.println("正在返回错误信息");
        //从异常对象中获取提示信息封装返回
        return ResponseResult.fail(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
