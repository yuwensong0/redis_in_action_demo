package com.example.redis_chapter01.controller;

import com.example.redis_chapter01.pojo.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuwensong
 * @date 2020/3/15
 */
@RestController
public class TestController {
    private static Integer index = 0;
    @GetMapping("/person")
    @Cacheable(value = "person", key = "#id")
    public Person findOne(String id) {
        Person person = new Person();
        person.setAge(index++);
        person.setName("小明");
        return person;
    }

    @GetMapping("/clear")
    @CacheEvict(value = "person")
    public void clear() {

    }

}
