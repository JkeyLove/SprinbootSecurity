package com;

import com.domain.User;
import com.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SangengApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){

        System.out.printf("666");

        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

}
