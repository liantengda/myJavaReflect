package com.lian.javareflect.test;

public class AImpl同学 implements 我的需求 {
    @Override
    public String 给我输出一千个哈() {
        String a = "" ;
        for(int i = 0;i<1000;i++){
            a = a + "哈";
        }
        return a;
    }
}
