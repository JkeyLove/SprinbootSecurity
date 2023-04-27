package com.handler;

import com.alibaba.fastjson.JSON;
import com.domain.ResponseResult;
import com.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *   SpringSecurity的权限鉴定拦截器要实现AccessDeniedHandler接口
 * */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "您的权限不足");
        String json = JSON.toJSONString(responseResult);

        //处理异常
        WebUtils.renderString(response,json);
    }
}
