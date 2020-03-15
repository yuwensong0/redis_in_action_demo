package com.example.redis_chapter01;

import com.example.redis_chapter01.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisChapter01ApplicationTests {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
        Person person = new Person();
        person.setAge(11);
        person.setName("小明");
        redisTemplate.opsForValue().set("a", person);
        Object a = redisTemplate.opsForValue().get("a");
        System.out.println((Person)a);
        System.out.println(a.getClass());
    }

}
