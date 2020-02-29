package com.lian.javareflect.stringabout;

public class Test {

    public static void main(String[] args) {
        String s = new String ("aaa");
        System.out.println("一开始s的hashcode"+s);
        String s1 = addB(s);
        System.out.println("函数调用s后的hashcode"+s);
        System.out.println("调用完函数后的hashcode"+s);

        s+="changeHashcod";

        System.out.println("赋值之后的s的hashcode"+s);

    }

    public static String addB(String s){
        return s+"b";
    }
}
