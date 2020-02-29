package com.lian.javareflect.User;

import com.lian.javareflect.JavareflectApplication;
import com.lian.javareflect.mapper.UserMapper;
import com.lian.javareflect.model.User;
import com.lian.javareflect.service.UserService;
import com.lian.javareflect.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


@SpringBootTest(classes = JavareflectApplication.class)
public class UserTest {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper  userMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    public  void addUser(){
        User user = new User();
        user.setId(1001);
        user.setUserName("zhazhahui");
        user.setPassWord("1111111");
        user.setRealName("渣渣灰");
        int add = userService.add(user);
    }
    @Test
    public void addUser2() throws ClassNotFoundException, NoSuchFieldException {
        User user = new User();
        user.setUserName("zhazhahui");
        user.setPassWord("1111111");
        user.setRealName("渣渣灰");
        new HashMap<>();
        //反射获取类模板
        Class<?> aClass = Class.forName("com.lian.javareflect.service.impl.UserServiceImpl");
        Field mapper = aClass.getDeclaredField("userMapper");
        mapper.setAccessible(true);

        try {
            Method add = aClass.getDeclaredMethod("jdbcAdd", User.class,JdbcTemplate.class);
            try {
                try {
                    UserServiceImpl userService = (UserServiceImpl)aClass.newInstance();
                    mapper.set(userService,this.userMapper);

                    add.invoke(userService,user,jdbcTemplate);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }



}
