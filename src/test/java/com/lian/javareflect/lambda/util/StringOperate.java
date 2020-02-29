package com.lian.javareflect.lambda.util;

public class StringOperate {
    /**
     * 加倍输出字符串
     * @param s
     */
    public static void doubleString(String s){
        s=s+s;
        System.out.println(s);
    }
    /**
     * 三倍输出字符串
     * @param s
     */
    public static void trebleString(String s){
        s = s+s+s;
        System.out.println(s);
    }
    /**
     * 鸡你太美字符串
     * @param s
     */
    public static void 鸡你太美String(String s){
        s = s+s+s+",鸡你太美";
        System.out.println(s);
    }
    /**
     * 冠状病毒字符串
     * @param s
     */
    public static void 冠状病毒String(String s){
        s = s+s+s+",鸡你太美，新年好，冠状病毒";
        System.out.println(s);
    }
}
