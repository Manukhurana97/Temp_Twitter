package com.example.viewandreplytweets.UserApiCall;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient("http://localhost:8000/")
public interface UserCalling {

    @PostMapping("/Account/CheckToken")
    Map<String, String> checktokenzuul(@RequestHeader(name = "Authentication") String token);
}

