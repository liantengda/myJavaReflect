package com.lian.javareflect.lambda;

/**
 * @author Ted
 * @date 2020/5/1 2:00
 */

public class Person {
    private Long personId;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getPersonId() {
        return personId;
    }
}
