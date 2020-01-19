package com.lian.javareflect.service;

import com.lian.javareflect.model.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
public interface UserService {
   /**
    *
    * @param id
    * @return
    */
   User sel(int id);

   /**
    *
    * @param user
    * @return
    */
   int add(User user);

   /**
    *
    * @param user
    * @param jdbcTemplate
    * @return
    * @throws ClassNotFoundException
    * @throws NoSuchMethodException
    * @throws IllegalAccessException
    * @throws InstantiationException
    * @throws InvocationTargetException
    */
   int jdbcAdd(User user, JdbcTemplate jdbcTemplate) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException;

   /**
    *
    * @param user
    * @return
    */
   User upd(User user);

   /**
    *
    * @param id
    * @return
    */
   User del(int id);
}
