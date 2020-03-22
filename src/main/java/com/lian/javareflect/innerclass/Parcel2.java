package com.lian.javareflect.innerclass;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 15:51
 */
public class Parcel2 {
    class Contents{
        private int i=11;
        public int value(){
            return i;
        }
    }

    class Destination{
        private String label;
        Destination(String whereTo){
            label=whereTo;
        }
        String readLabel(){
            return label;
        }
    }

    public Destination to(String s){
        return new Destination(s);
    }

    public Contents contents(){
        return new Contents();
    }

    public void ship(String dest){
        Contents contents = contents();
        Destination destination = to(dest);
        System.out.println(destination.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 parcel2 = new Parcel2();
        parcel2.ship("上海");
        Parcel2 q = new Parcel2();
        Contents contents = q.contents();
        Destination destination = q.to("沈阳");
    }

}
