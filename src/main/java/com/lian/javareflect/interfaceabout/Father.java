package com.lian.javareflect.interfaceabout;

public class Father implements Operate {

    private final String name;

    public Father(String name){
        this.name = name;
    }
    @Override
    public void talk(String s) {
        System.out.println(name + s);
    }

    /**
     * 父亲赚大钱方法，只属于父亲角色
     */
    private void makeBigMoney(){
        System.out.println("I need to make some money to make a live");
    }
}
