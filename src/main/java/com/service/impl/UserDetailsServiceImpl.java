package com.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.domain.LoginUser;
import com.domain.User;
import com.mapper.MenuMapper;
import com.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户,就抛出异常
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }



        //TODO 查询对应的权限信息
        /*List<String> list = new ArrayList<>(Arrays.asList("test","admin"));*/
        List<String> list = menuMapper.selectPermsByUserId(1L);
        //把数据封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
