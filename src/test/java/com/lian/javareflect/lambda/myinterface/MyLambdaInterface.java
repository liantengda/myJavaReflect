package com.lian.javareflect.lambda.myinterface;


/**
 * 函数式接口
 * 只允许实现一个接口函数
 */
@FunctionalInterface
public interface MyLambdaInterface {
    /**
     * 对字符串做同一件事情
     * @param s
     */
    void doSameThing(String s);
}
