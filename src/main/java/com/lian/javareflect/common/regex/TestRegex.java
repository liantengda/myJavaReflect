package com.lian.javareflect.common.regex;

import com.lian.javareflect.common.regex.model.Article;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式中的几个符号
 * + 表示{1，}起码一个
 * ？表示（0，1）最多一个
 * * 表示{0，}不出现或者任意次数
 */
public class TestRegex {
    public static void main(String[] args) {
        String html = HttpGetUtil.getHtml();
        String reg = "href=\".+?\"";
        HttpGetUtil.printMatchResult(html,reg);
    }
}
/**
 * 一个内部类
 */
class HttpGetUtil{
//虚拟的http请求客户端
public static final CloseableHttpClient httpClient = HttpClients.createDefault();
//响应对象
public static  CloseableHttpResponse response = null;
    /**
     * 获取网页源代码
     * @return
     */
    public static String getHtml(){
        HttpGet get = new HttpGet("http://www.cnblogs.com/");
        get.addHeader("Content-type", "application/x-www-form-urlencoded");
        get.addHeader("Content-length","0");
        get.addHeader("accept", "application/json");
        get.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.7.6)");
        //发起httpClient请求
        System.out.println("准备发起请求");
        try {
            response = httpClient.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取请求返回的对象
        HttpEntity entity = response.getEntity();
        String entityStr = null;
        //生成响应体内容
        if(entity != null){
            try {
              entityStr = EntityUtils.toString(entity,"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return entityStr;
    }
    /**
     * 根据正则表达式打印源代码中指定的字符串
     * @param html 待匹配源代码
     * @param reg 要求的正则表达式
     * @return
     */
    public static void printMatchResult(String html,String reg){
        int count = 0;
        ArrayList<Article> articles = new ArrayList<>();
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()){ //居然是个游标
            System.out.println(matcher.group());//取出每次找到的匹配结果
            count++;
        }
        System.out.println("一共有多少个这样的字符串"+count);
    }
}