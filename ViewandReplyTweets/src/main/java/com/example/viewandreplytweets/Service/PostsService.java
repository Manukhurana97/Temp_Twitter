package com.example.viewandreplytweets.Service;

import com.example.viewandreplytweets.Models.Posts;
import com.example.viewandreplytweets.Response.lstPostsResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PostsService {

    public List<lstPostsResponse> findAllPosts() throws ExecutionException, InterruptedException;

    public List<lstPostsResponse> findAllByUserAndOrderByDateDesc(String user) throws ExecutionException, InterruptedException;

    public int getUserPostsCount(String usercount) throws ExecutionException, InterruptedException;

    public Posts getPostsinfo(String postid, String user) throws ExecutionException, InterruptedException;
}
