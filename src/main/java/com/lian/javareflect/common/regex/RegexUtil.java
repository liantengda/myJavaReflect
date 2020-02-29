package com.lian.javareflect.common.regex;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    /**
     * 检测身份证号合不合乎规范
     * @param IDCard  待检测身份证号码
     * @return
     */
    public static String checkIDCard(String IDCard){
        String regex = "(^[1-9]\\d{5}(19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(IDCard);
        if(matcher.find()){
            System.out.println("分组个数---》"+matcher.groupCount()+"个");
            System.out.println("最大分组---》"+matcher.group());
            System.out.println("最大分组---》"+matcher.group(0));
            for(int i=1;i<matcher.groupCount();i++){
                System.out.println("分组"+i+"--->"+matcher.group(i));
            }
            return "身份证号--->"+IDCard+"合乎规范";
        }else {
            return "身份证号--->"+IDCard+"不合乎规范";
        }
    }
}
