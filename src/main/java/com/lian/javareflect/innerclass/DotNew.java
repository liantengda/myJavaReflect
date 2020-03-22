package com.lian.javareflect.innerclass;

/**
 * 内部类创建的前提条件是什么。一定要先有外部类，内部类才能有依附点。
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 21:57
 */
public class DotNew {
    private class Inner{
        private DotNew outer(){
            return DotNew.this;
        }
    };

    public static void main(String[] args) {
        DotNew dotNew = new DotNew();
        Inner inner = dotNew.new Inner();
        DotNew outer = inner.outer();
        System.out.println(outer==dotNew);
    }
}
