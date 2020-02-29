package com.lian.javareflect.interfaceabout;

import java.util.Iterator;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> integers = new LinkedList<>();
        for(int i = 0;i<10;i++){
            integers.add(i);
        }
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        for(int i=0;i<=10;i+=2){
            System.out.print("哈");
        }
    }
}
