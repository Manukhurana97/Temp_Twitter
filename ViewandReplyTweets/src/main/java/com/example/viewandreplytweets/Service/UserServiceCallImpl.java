package com.example.viewandreplytweets.Service;

import com.example.viewandreplytweets.Dao.CommentsDao;
import com.example.viewandreplytweets.Dao.PostDao;
import com.example.viewandreplytweets.Models.Posts;
import com.example.viewandreplytweets.UserApiCall.UserCallValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@org.springframework.stereotype.Service
public class UserServiceCallImpl implements  UserService{

    @Autowired
    public PostDao postDao;

    @Autowired
    public CommentsDao commentsDao;

    @Autowired
    public UserCallValidation userCallValidation;

    @Async
    protected CompletableFuture<Map<String, String>> usercall_thread (String token){
        return  CompletableFuture.supplyAsync( () ->  userCallValidation.usercall(token));
    }

    public Map.Entry<String, String> usercall(String token) throws ExecutionException, InterruptedException {
        CompletableFuture<Map<String, String>> map = usercall_thread(token);
        Map<String, String> user = map.get();
        return user.entrySet().iterator().next();
    }


}
