package com.lian.javareflect.service.impl;


import com.lian.javareflect.mapper.UserMapper;
import com.lian.javareflect.mapper.impl.UserMapperImpl;
import com.lian.javareflect.model.User;
import com.lian.javareflect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User sel(int id){
        return userMapper.sel(id);
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int jdbcAdd(User user, JdbcTemplate jdbcTemplate) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println("我找到这个方法了----->");
        Class<?> aClass = Class.forName("com.lian.javareflect.mapper.impl.UserMapperImpl");
        Method jdbcAdd = aClass.getDeclaredMethod("jdbcAdd", User.class,JdbcTemplate.class);
        UserMapperImpl userMapper = (UserMapperImpl)aClass.newInstance();
        jdbcAdd.invoke(userMapper,user,jdbcTemplate);
        return 0;
    }

    @Override
    public User upd(User user) {
        return userMapper.upd(user);
    }

    @Override
    public User del(int id) {
        return userMapper.del(id);
    }
}
