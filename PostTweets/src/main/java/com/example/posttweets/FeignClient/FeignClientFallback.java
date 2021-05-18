package com.example.posttweets.FeignClient;


import com.example.posttweets.UserApiCall.UserCalling;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;

public class FeignClientFallback implements UserCalling {


    public Map<String, String> checktokenzuul(@RequestHeader(name = "Authentication") String token){
        Map<String, String> map = new HashMap<>();
        return map;
    }

}
