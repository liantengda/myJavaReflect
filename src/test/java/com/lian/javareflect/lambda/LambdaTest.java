package com.lian.javareflect.lambda;


import com.lian.javareflect.lambda.myinterface.MyLambdaInterface;
import com.lian.javareflect.lambda.util.StringOperate;

public class LambdaTest {
    public static void main(String[] args) {
        MyLambdaInterface myLambdaInterface = s -> StringOperate.鸡你太美String(s);

        //进行一波操作
        myLambdaInterface.doSameThing("哈");
        myLambdaInterface.doSameThing("呵");
        myLambdaInterface.doSameThing("嘿");
        myLambdaInterface.doSameThing("略");
        myLambdaInterface.doSameThing("汪");
        myLambdaInterface.doSameThing("啦");
        myLambdaInterface.doSameThing("嘎");
        myLambdaInterface.doSameThing("hia");
        myLambdaInterface.doSameThing("six");
    }
}
