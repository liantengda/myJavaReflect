package com.lian.javareflect.innerclass;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 22:04
 */
public class Parcel3 {
    class Contents{
        private int i = 11;
        public int value(){
            return i;
        }
    }
    class Destination{
        private String label;
        Destination(String whereTo){
            label = whereTo;
        }
        String readLine(){return label;}
    }

    public static void main(String[] args) {
        Parcel3 parcel3 = new Parcel3();
        Contents contents = parcel3.new Contents();
        Destination 北京 = parcel3.new Destination("北京");
    }
}
