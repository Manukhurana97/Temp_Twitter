package com.example.viewandreplytweets.FeignClient;



import com.example.viewandreplytweets.UserApiCall.UserCalling;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;

public class FeignClientFallback implements UserCalling {


    public Map<String, String> checktokenzuul(@RequestHeader(name = "Authentication") String token){
        Map<String, String> map = new HashMap<>();
        return map;
    }

}
