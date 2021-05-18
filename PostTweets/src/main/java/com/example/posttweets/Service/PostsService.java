package com.example.posttweets.Service;

import com.example.posttweets.Models.Posts;

import java.util.concurrent.ExecutionException;

public interface PostsService {

    public Posts findByPostid(String postid, String user) throws ExecutionException, InterruptedException;
}
