package com.example.redis_chapter01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisChapter01Application {

    public static void main(String[] args) {
        SpringApplication.run(RedisChapter01Application.class, args);
    }

}
