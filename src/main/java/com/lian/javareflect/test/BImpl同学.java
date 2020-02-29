package com.lian.javareflect.test;

public class BImpl同学 implements 我的需求 {
    @Override
    public String 给我输出一千个哈() {
        String a = "";
        int i = 0;
        do{
            a = a + "哈";
            i++;
        } while(i<1000);
        return a;
    }
}
