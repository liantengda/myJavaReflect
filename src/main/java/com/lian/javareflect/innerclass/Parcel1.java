package com.lian.javareflect.innerclass;

/**
 * 包裹类
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 9:53
 */
public class Parcel1 {
    /**
     * 内容
     */
    class Contents{
        private int i = 11;
        public int value(){
            return i;
        }
    }

    /**
     * 目的地
     */
    class Destination{
        private String label;
        Destination(String whereTo){
            label = whereTo;
        }
        String readLabel(){
            return label;
        }
    }

    /**
     * 搬运到目的地
     * @param dest
     */
    public void ship(String dest){
        Contents c  = new Contents();
        Destination destination = new Destination(dest);
        System.out.println(destination.readLabel());
    }

    public static void main(String[] args) {
        Parcel1 parcel1 = new Parcel1();
        parcel1.ship("北京");
    }

}
