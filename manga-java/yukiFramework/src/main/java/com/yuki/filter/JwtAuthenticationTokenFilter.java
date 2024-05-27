package com.yuki.filter;

import com.yuki.enums.AppHttpCodeEnum;
import com.yuki.exception.SystemException;
import com.yuki.security.LoginUser;
import com.yuki.utils.JwtUtil;
import com.yuki.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = httpServletRequest.getHeader("token");
        // 如果token为空
        if (!StringUtils.hasText(token)){
//            if (httpServletRequest.getRequestURI().equals("/user/logout")){
//                throw new RuntimeException("请先登录");
//            }
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        // 解析token获取userid
        String userid = "";
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        // 使用uesrId从redis中获取完整的用户信息
        String tokenKey = "userid:"+userid;
        LoginUser loginUser = redisCache.getCacheObject(tokenKey);
        if (Objects.isNull(loginUser)){
            // 要么是真没登录，要么是token过期了
//            throw new SystemException(AppHttpCodeEnum.NEED_LOGIN);
            resolver.resolveException(httpServletRequest, httpServletResponse, null, new SystemException(AppHttpCodeEnum.NEED_LOGIN));
            return;
        }
        // 存入SecurityContentHolder中
        // TODO 获取权限信息,存入usernamePasswordAuthenticationToken中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
