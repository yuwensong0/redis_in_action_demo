package com.example.redis_chapter01.pojo;

/**
 * @author yuwensong
 * @date 2020/3/21
 */
public class User {
    private String id;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
