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

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest(classes = JavareflectApplication.class)
public class UserTest {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper  userMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public  void addUser(){
        User user = new User();
        user.setUserName("Ted");
        user.setPassWord("hehe");
        user.setRealName("古天乐");
        userService.add(user);
    }

    @Test
    public void addUser2() throws ClassNotFoundException, NoSuchFieldException {
        User user = new User();
        user.setUserName("Ted");
        user.setPassWord("hehe");
        user.setRealName("古天乐");
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

    /**
     * 通过JDK的反射，获取类的所有信息
     * @param clz   类的Class对象。类的模板
     * @return
     */
    private Map<String,List<String>> getMethodInfo(Class<?> clz){
        Map<String, List<String>> classInfo = new HashMap<>();
        Method[] methods = clz.getDeclaredMethods();
        for(int i=0;i<methods.length;i++){
            ArrayList<String> methodInfo = new ArrayList<>();
            if(methods[i]!=null){
                methodInfo.add("methodName:"+methods[i].getName());
                methodInfo.add("method modifier:"+ Modifier.toString(methods[i].getModifiers()));
                Parameter[] parameters = methods[i].getParameters();
                for (int j=0;j<parameters.length;j++){
                    methodInfo.add(parameters[j].getName()+":"+parameters[j].getType());
                }
                methodInfo.add("return type:"+methods[i].getReturnType());
                classInfo.put("method"+(i+1),methodInfo);
            }
        }
        return classInfo;
    }

    public static void main(String[] args) {
        UserTest userTest = new UserTest();
        Map<String, List<String>> methodInfo = userTest.getMethodInfo(UserService.class);
        methodInfo.forEach((key,value)->{
            System.out.println(key+"---->"+value);
        });
    }

}
