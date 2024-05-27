package com.yuki.handler;

import com.alibaba.fastjson.JSON;
import com.yuki.domain.ResponseResult;
import com.yuki.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    // 如果在用户认证的过程中出现错误,这里会返回错误提示
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户名或密码错误");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse,json);
    }
}
