package com.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {
    @RequestMapping("/hello")
    /*@PreAuthorize("hasAuthority('system/dept/list')")*/
    @PreAuthorize("@ex.hasAuthority('system/dept/list')")
    public String hello1(){
        return "hello";
    }

    //使用的是基于配置的权限校验
    @RequestMapping("/happy")
    public String sayHappy(){
        return "happy";
    }
}
