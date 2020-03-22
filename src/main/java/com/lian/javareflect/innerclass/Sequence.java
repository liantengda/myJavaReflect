package com.lian.javareflect.innerclass;

import com.lian.javareflect.innerclass.innerinterface.Selector;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 21:29
 */
public class Sequence {
    private Object[] items;
    private int next = 0;
    public Sequence(int size){
        items = new Object[size];
    }
    public void add(Object x){
        if(next<items.length){
            items[next++]=x;
        }
    }
    private class SequenceSelector implements Selector{
        private int i=0;
        @Override
        public boolean end() {
            return items.length==i;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if(i<items.length){
                i++;
            }
        }
    }

    public Selector selector(){
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for(int i=0;i<10;i++){
            sequence.add(Integer.toString(i));
        }
        Selector selector = sequence.selector();
        while (!selector.end()){
            System.out.println(selector.current()+" ");
            selector.next();
        }
    }
}
