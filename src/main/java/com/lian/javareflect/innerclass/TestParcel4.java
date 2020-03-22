package com.lian.javareflect.innerclass;

import com.lian.javareflect.innerclass.innerinterface.Contents;
import com.lian.javareflect.innerclass.innerinterface.Destination;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/3/22 22:30
 */
public class TestParcel4 {
    public static void main(String[] args) {
        Parcel4 parcel4 = new Parcel4();
        Contents contents = parcel4.contents();
        Destination destination = parcel4.destination("北京");
        Destination Destination = parcel4.new PDestination("北京");
        System.out.println(destination==Destination);
    }
}
