package com.lian.javareflect.innerclass;

import com.lian.javareflect.innerclass.innerinterface.Contents;
import com.lian.javareflect.innerclass.innerinterface.Destination;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 22:20
 */
public class Parcel4 {
    private class PContents implements Contents{
        private int i =11;
        @Override
        public int value() {
            return i;
        }
    }
    protected class PDestination implements Destination{
        private String label;
        @Override
        public String readLabel() {
            return label;
        }
        public PDestination(String whereTo){
            label = whereTo;
        }
    }

    public Destination destination(String s){
        return new PDestination(s);
    }

    public Contents contents(){
        return new PContents();
    }
}
