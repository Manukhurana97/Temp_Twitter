package com.example.posttweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients
@SpringBootApplication
@EnableAsync
public class PostTweetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostTweetsApplication.class, args);
    }

}
