package com.example.redis_chapter01.pojo;

import java.io.Serializable;

/**
 * @author yuwensong
 * @date 2020/3/15
 */
public class Person  implements Serializable{
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
