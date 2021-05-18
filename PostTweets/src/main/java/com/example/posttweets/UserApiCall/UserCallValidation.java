package com.example.posttweets.UserApiCall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserCallValidation {

    @Autowired
    public UserCalling userCalling;

    public synchronized Map<String, String> usercall(java.lang.String token) {
        java.util.Map<java.lang.String, java.lang.String> map = userCalling.checktokenzuul(token);
        Map.Entry<java.lang.String, java.lang.String> userinfo = map.entrySet().iterator().next();

        // find playlist and check if user is creater of playlist

        if (userinfo.getKey() == null) {
            throw new SecurityException("Unauthorized user");
        }
        return map;

    }

}
