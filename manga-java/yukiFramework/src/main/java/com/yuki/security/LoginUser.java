package com.yuki.security;

import com.alibaba.fastjson.annotation.JSONField;
import com.yuki.domain.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private SysUser user;

    // 存储权限的信息
    private List<String> perms;

    public LoginUser(SysUser user, List<String> perms) {
        this.user = user;
        this.perms = perms;
    }

    //
//    public LoginUser(SysUser user, List<String> perms) {
//        this.user = user;
//        this.perms = perms;
//    }

    // 存储springsecurity所需要的权限信息的集合
    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;


    // 获取权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null){
            return authorities;
        }
        // 把perms中字符串类型的权限信息转化为GrantedAuthority对象存入authorities中
        authorities = perms.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    // 获取密码
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 获取用户名
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否被锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否生效
    @Override
    public boolean isEnabled() {
        return true;
    }
}
