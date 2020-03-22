package com.example.redis_chapter01;

import com.example.redis_chapter01.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class RedisChapter01ApplicationTests {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
//        redisTemplate.opsForSet().add("set1", "a", "b", "c");
        redisTemplate.opsForSet().add("set2", "b", "e", "d");
//        redisTemplate.opsForZSet().add("zset2", "c", 100);
        redisTemplate.opsForZSet().add("zset2", "e", 133);
        List<String> strings = Arrays.asList("set2");
        redisTemplate.opsForZSet().intersectAndStore("zset2", strings, "set1", RedisZSetCommands.Aggregate.SUM);

    }

}
