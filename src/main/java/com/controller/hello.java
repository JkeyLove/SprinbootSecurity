package com.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hello {

    @RequestMapping("/hello")
    @PreAuthorize("hasAuthority('system/dept/list')")
    public String hello1(){
        return "hello";
    }

}
