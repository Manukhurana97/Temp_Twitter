package com.example.viewandreplytweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ViewandReplyTweetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViewandReplyTweetsApplication.class, args);
    }

}
