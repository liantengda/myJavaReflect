package com.lian.javareflect.lambda.myinterface.impl;

import com.lian.javareflect.lambda.myinterface.MyInterface;
import com.lian.javareflect.lambda.util.StringOperate;

public class MyInterfaceImpl implements MyInterface {
    /**
     * 对字符串做同一间事情
     * @param s
     */
    @Override
    public void doSameThingToString(String s) {
        StringOperate.鸡你太美String(s);
    }
}
