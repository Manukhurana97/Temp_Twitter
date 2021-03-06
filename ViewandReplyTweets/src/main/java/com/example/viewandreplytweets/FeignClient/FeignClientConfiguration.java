package com.example.viewandreplytweets.FeignClient;

import feign.Logger;
import feign.codec.ErrorDecoder;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {

    @Bean
    public OkHttpClient client(){
        return new OkHttpClient();
    }


    @Bean
    public Logger.Level feignLoggingLevel(){
        return Logger.Level.FULL;
    }


    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignclientErrorHanding();
    }
}
