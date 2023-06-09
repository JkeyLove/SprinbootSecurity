package com.service.impl;

import com.domain.LoginUser;
import com.domain.ResponseResult;
import com.domain.User;
import com.service.LoginService;
import com.utils.JwtUtil;
import com.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //AuthenticationManager  authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        //会调用UserDetailsServiceImpl的loadUserByUsername方法进行用户校验,然后把User封装成UserDetails(也就是LoginUser)返回
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没有通过,给出对应的提示(在UserDetailsService已经给出提示了?)
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了,使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis userid作为key
        redisCache.setCacheObject("login:"+userid,loginUser);

        return new ResponseResult<>(200,"登录成功",map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult<>(200,"登出成功");
    }


}
