package com.example.posttweets.Service;

import com.example.posttweets.Dao.PostDao;
import com.example.posttweets.Models.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PostServiceImpl implements PostsService{


    @Autowired
    public PostDao postDao;

    @Async
    public CompletableFuture<Posts> postscall_thread(String postid, String user) throws ExecutionException, InterruptedException {
        return  CompletableFuture.supplyAsync(() -> postDao.findPostsByPostidAndUser(postid, user));
    }

    public Posts findByPostid(String postid, String user) throws ExecutionException, InterruptedException{
        CompletableFuture<Posts> completableFuture = postscall_thread(postid, user);
        return completableFuture.get();
    }
}
