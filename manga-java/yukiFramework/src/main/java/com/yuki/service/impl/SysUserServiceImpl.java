package com.yuki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuki.domain.ResponseResult;
import com.yuki.domain.dto.RegisterUser;
import com.yuki.domain.dto.UpdateUser;
import com.yuki.domain.entity.SysUser;
import com.yuki.domain.vo.UserVo;
import com.yuki.enums.AppHttpCodeEnum;
import com.yuki.mapper.SysUserMapper;
import com.yuki.security.LoginUser;
import com.yuki.service.SysUserService;
import com.yuki.utils.JwtUtil;
import com.yuki.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-11-15 16:40:20
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public ResponseResult userLogin(SysUser user) {

        // 使用AuthenticationManager authenticate 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 如果认证失败,抛出异常
        if (Objects.isNull(authenticate)){
            return ResponseResult.fail(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),"登录失败");
//            throw new RuntimeException("登录失败");
        }
        // 如果认证成功,则使用userid生成一个jwt,jwt存入ResponseResult中返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
//        System.out.println(userid);
        String jwt = JwtUtil.createJWT(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        // 将完整的用户信息存入redis中
        redisCache.setCacheObject("userid:"+userid, loginUser);
        return ResponseResult.success(HttpStatus.OK.value(),"登录成功",map);
    }


    /**
     * 用户登出
     * @return
     */
    @Override
    public ResponseResult logout() {
        // 获取SecurityContentHolder中的id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        Long userid = loginUser.getUser().getId();
        //  删除redis中的值
        redisCache.deleteObject("userid:"+userid);
        return ResponseResult.success(HttpStatus.OK.value(),"退出登录成功!");
    }

    /**
     * 用户注册
     * @param user 用户传过来的数据
     * @return
     */
    @Override
    public ResponseResult userRegister(RegisterUser user) {

        String username = user.getUsername();
        SysUser sysUser_temp = this.GetUserByUsername(username);
        // 查看用户名是否重复
        if (!Objects.isNull(sysUser_temp)){
            if (user.getUsername().equals(sysUser_temp.getUsername())){
                return ResponseResult.fail(400,"该用户已存在");
            }
        }
        // 两次密码是否一次
        if (!user.getPassword().equals(user.getConfirm())){
            System.out.println(">>>>>>>>>>>>>>>>>>>");
            System.out.println(user.getPassword());
            System.out.println(user.getConfirm());
//            throw new RuntimeException("两次密码不一致");
             return ResponseResult.fail(400,"两次密码不一致");
        }

        if (user.getUsername().length() < 6 || user.getPassword().length()< 6){
            return ResponseResult.fail(400,"用户名和密码的长度必须不小于6");
        }

        user.setCreatedate(LocalDateTime.now());
        SysUser sysUser = new SysUser();
        // 添加用户名
        sysUser.setUsername(user.getUsername());
        // 加密密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        // 添加密码
        sysUser.setPassword(password);
        sysUser.setCreatedate(user.getCreatedate());
        // 添加状态，默认 启用
        sysUser.setState(1);
        // 添加权限, 默认 普通用户
        sysUser.setRoleId(Integer.valueOf(2));
        // 保存，实现用户注册
        boolean save = this.save(sysUser);
        System.out.println(save);

        return ResponseResult.success(200,"用户注册成功!");
    }

    /**
     * 通过用户名获取对应实体类
     * @param username
     * @return
     */
    @Override
    public SysUser GetUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        SysUser sysUser = this.getOne(queryWrapper);
        return sysUser;
    }

    @Override
    public ResponseResult getUserInfo() {
        // 获取SecurityContentHolder中的id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String username = loginUser.getUser().getUsername();

        // 获取这个用户的信息
        SysUser sysUser = this.GetUserByUsername(username);

        UserVo userVo = new UserVo();
        // 添加用户id
        userVo.setId(sysUser.getId());
        // 添加用户名
        userVo.setUsername(sysUser.getUsername());
        // 添加邮箱
        userVo.setEmail(sysUser.getEmail());
        // 添加生日
        userVo.setBirthday(sysUser.getBirthday());
        // 添加头像
        userVo.setAvatar(sysUser.getAvatar());
        // 添加权限，先通过用户id多表查询到权限名
        String roleName = userMapper.selectRoleNameById(sysUser.getId());
        userVo.setRolename(roleName);
        // 添加创建日期
        userVo.setCreatedate(sysUser.getCreatedate());
        // 添加修改日期,如果没有，就返回空
        if (Objects.isNull(sysUser.getUpdatedate())){
            userVo.setUpdatedate(null);
        }else{
            userVo.setUpdatedate(sysUser.getUpdatedate());
        }

        return ResponseResult.success(200,"获取用户信息成功!",userVo);
    }

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    @Override
    public ResponseResult updateUserData(UpdateUser user) {
        // 获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String username = user.getUsername();
        SysUser sysUser_temp = this.GetUserByUsername(username);
        // 查看用户名是否重复

        List<String> list = GetAllUserList(username);
        for (int i=0;i<list.size();i++){
            if (list.get(i).equals(username)){
                return ResponseResult.fail(400,"该用户已经存在");
            }
        }

        System.out.println(user.getUsername());
        System.out.println(user.getBirthday());
        System.out.println(user.getPassword());

        if (Objects.isNull(user.getUsername())){
            return ResponseResult.fail(400,"请完善用户信息");
        }



        // 获取当前用户的唯一标识符
        Long userid = loginUser.getUser().getId();

        SysUser sysUser = new SysUser();
        // 设置头像
        sysUser.setAvatar(user.getAvatar());
        // 设置用户名
        sysUser.setUsername(user.getUsername());
        // 设置加密密码,如果传过来的password为空,那么则不进行密码的更改
        if (!Objects.isNull(user.getPassword())){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(user.getPassword());
            sysUser.setPassword(password);
        }
        // 设置生日
        sysUser.setBirthday(user.getBirthday());
        // 设置邮箱
        sysUser.setEmail(user.getEmail());
        // 设置更新日期
        sysUser.setUpdatedate(LocalDateTime.now());

        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",userid);
        boolean update = this.update(sysUser,updateWrapper);
        return ResponseResult.success(200,"修改成功!",update);
    }

    @Override
    public ResponseResult userAuthentication(String value) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        // 因为发送过来会有问题(最后一位会多加1个 '='),所以删去最后一位
        value = value.substring(0, value.length() - 1);
        System.out.println(">>>>>>>>>>>>>>>");
        System.out.println("--"+value+"--");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(value, loginUser.getUser().getPassword());
        if (matches){
            return ResponseResult.success(200,"验证成功!",matches);
        }else{
            return ResponseResult.success(400,"验证失败!",matches);
        }
    }

    /**
     * 获取sys_user中除了当前用户名的所有用户名
     * @return
     */
    @Override
    public List<String> GetAllUserList(String curruser) {
        List<String> list = userMapper.selectAllUserName(curruser);
        return list;
    }
}

