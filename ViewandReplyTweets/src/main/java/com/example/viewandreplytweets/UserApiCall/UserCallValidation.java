package com.example.viewandreplytweets.UserApiCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserCallValidation {

    @Autowired
    public UserCalling userCalling;

    public synchronized Map<String, String> usercall(String token) {
        Map<String, String> map = userCalling.checktokenzuul(token);
        Map.Entry<String, String> userinfo = map.entrySet().iterator().next();

        // find playlist and check if user is creater of playlist

        if (userinfo.getKey() == null) {
            throw new SecurityException("Unauthorized user");
        }
        return map;

    }

}
