package com.lian.javareflect.common.utils;

public class StringUtil {

    public static void main(String[] args) {
        // 1开头 5个整数  19开头或者2几或者3几开头  连个整数  共四个表示年份  然后以0开头 +1-9或者10，或者11或者12，表示月份， 0-2里面选一个+1到9 或者10或者20或者30或者31号作为天号，三个数字+1位数字或者Xx表示顺序码加校验码
        // 1开头 五个整数 再来两个整数表示年份（19年的）然后继续表示月份+日期，一摸一样 再加两个整数然后+一个整数，听说最后以为表示，单数男，双数女。
        String s = "(^[1-9]\\d{5}(19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
        String s1 = StringUtil.replaceGang(s);
        System.out.println(s1);
    }
    /**
     * 将字符串中的双斜杠换成单斜杠
     * @param s
     * @return
     */
    public static String replaceGang(String s){
       s =  s.replace("\\","\\");
        return s;
    }
}
