package com.example.posttweets.Service;

import com.example.posttweets.Dao.PostDao;
import com.example.posttweets.Models.Posts;
import com.example.posttweets.Request.PostsRequest;
import com.example.posttweets.UserApiCall.UserCallValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public PostDao postDao;

    @Autowired
    public UserCallValidation userCallValidation;

    @Async
    public CompletableFuture<Map<String, String>> usercall_thread(String token) throws ExecutionException, InterruptedException {
        return  CompletableFuture.supplyAsync( () ->  userCallValidation.usercall(token));
    }

    public Map.Entry<String, String> usercall(String token) throws ExecutionException, InterruptedException {
        CompletableFuture<Map<String, String>> map = usercall_thread(token);
        Map<String, String> user = map.get();
        return user.entrySet().iterator().next();
    }


}
