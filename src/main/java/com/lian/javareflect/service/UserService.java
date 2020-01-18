package com.lian.javareflect.service;

import com.lian.javareflect.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
public interface UserService {
   User sel(int id);

   int add(User user);

   int jdbcAdd(User user, JdbcTemplate jdbcTemplate) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException;

   User upd(User user);

   User del(int id);
}
