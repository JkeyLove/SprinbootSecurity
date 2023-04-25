package com;

import com.domain.User;
import com.mapper.MenuMapper;
import com.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SangengApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //$2a$10$JqmtKrKssXIsgM0glyaMAOXtOpNNsEs/7cCCLK.4ikNsy4DplB6vS是一个数据库的密文密码
        System.out.println(passwordEncoder.matches("1234",
                "$2a$10$.Fj9rNCH6dR4E3zLg.yJyuxsoMUKgqdeJ/pN6RKxH/oNEKWLtmURO"));

        /*String encode = passwordEncoder.encode("1234");
        String encode1 = passwordEncoder.encode("1234");
        System.out.println(encode);
        System.out.println(encode1);*/

    }

    @Test
    public void testUserMapper(){

        System.out.printf("666");

        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void selectPermsByUserId(){
        List<String> list = menuMapper.selectPermsByUserId(1L);
        System.out.println(list);
    }


}
