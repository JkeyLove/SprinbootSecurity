package com.service;

import com.domain.ResponseResult;
import com.domain.User;

public interface LoginService {


    ResponseResult login(User user);

    ResponseResult logout();
}
