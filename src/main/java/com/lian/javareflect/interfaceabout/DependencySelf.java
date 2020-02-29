package com.lian.javareflect.interfaceabout;


public class DependencySelf implements Operate {

    private DependencySelf dependencySelf;

    private void output(DependencySelf dependencySelf){
        System.out.println(dependencySelf.toString());
    }

    @Override
    public void talk(String s) {

    }
}
