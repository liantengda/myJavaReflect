package com.lian.javareflect.common.javaio;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestInputStream {

    public static void main(String[] args) {
        try {
            //字节流只能写入byte，这种写法会自动创建文件吗
            FileOutputStream fileOutputStream = new FileOutputStream("D:/log.log");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("hehe"+"\r\n");
            stringBuilder.append("haha");
           fileOutputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
