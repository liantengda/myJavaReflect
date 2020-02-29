package com.lian.javareflect.interfaceabout;

public class Child extends Father implements Operate{

    public static final int IQ = 300;

    private static final double PI = 3.15;

    public Child(String name) {
        super(name);
    }

    public void talk(String s){
        System.out.println("child-----llalalaal");
    }


}
