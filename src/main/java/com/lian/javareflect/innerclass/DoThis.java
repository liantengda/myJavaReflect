package com.lian.javareflect.innerclass;

/**
 * 内部类如何找到自己的外部类对象
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 21:48
 */
public class DoThis {
    void f(){
        System.out.println("DoThis.f()");
    }

    public class Inner{
        public DoThis outer(){
            return DoThis.this;
        }
    }
    public Inner inner(){
        return new Inner();
    }

    public static void main(String[] args) {
        DoThis doThis = new DoThis();
        Inner inner = doThis.inner();
        DoThis outer = inner.outer();
        System.out.println(doThis==outer);
    }
}
