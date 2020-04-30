package com.lian.javareflect.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Ted
 * @date 2020/5/1 1:59
 */
public class lambdaSummary {
    public static void main(String[] args) {
        Person black = new Person("小黑", 11L);
        Person red = new Person("小红",12L);
        Person yellow = new Person("小黄", 13L);
        Person green = new Person("小绿", 15L);
        Person red2 = new Person("小红",12L);
        Person green2 = new Person("小绿", 15L);
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(black);
        personArrayList.add(red);
        personArrayList.add(yellow);
        personArrayList.add(green);
        personArrayList.add(red2);
        personArrayList.add(green2);
        personArrayList.forEach(value-> System.out.println(value));
        System.out.println("---------->");
        ArrayList<Person> list2 = new ArrayList<>();
        //根据特定属性去重
        personArrayList.stream().collect(Collectors.groupingBy(p->p.getName(),Collectors.summingLong(Person::getPersonId))).forEach((name, personId)-> System.out.println("name:"+name+":"+"personId"+personId));
        personArrayList.forEach(value-> System.out.println(value));
        //将某属性集合到一个集合中
        List<String> collect = personArrayList.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(collect);
        //将某属性去重后并入一个集合中
        TreeSet<String> collect1 = personArrayList.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect1);
        //将某属性转换为字符串并连接起来
        String collect2 = personArrayList.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println(collect2);
        //计算某属性的加和
        Long collect3 = personArrayList.stream().collect(Collectors.summingLong(Person::getPersonId));
        System.out.println(collect3);
        //根据某属性将对象分组
        Map<Long, List<Person>> collect4 = personArrayList.stream().collect(Collectors.groupingBy(Person::getPersonId));
        System.out.println(collect4);
        //根据某属性将对象分组，分组和再加和某属性
        Map<String, Long> collect5 = personArrayList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.summingLong(Person::getPersonId)));
        System.out.println(collect5);
    }
}
