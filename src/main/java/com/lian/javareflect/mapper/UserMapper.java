package com.lian.javareflect.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lian.javareflect.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    User sel(int id);

    int add(User user);

    int jdbcAdd(User user, JdbcTemplate jdbcTemplate);

    User upd(User user);

    User del(int id);

    List<User> list();
}
