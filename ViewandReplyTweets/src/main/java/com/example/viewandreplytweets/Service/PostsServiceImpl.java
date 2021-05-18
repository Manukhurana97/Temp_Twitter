package com.example.viewandreplytweets.Service;

import com.example.viewandreplytweets.Dao.Hibernate.PostsHibernate;
import com.example.viewandreplytweets.Dao.PostDao;
import com.example.viewandreplytweets.Models.Posts;
import com.example.viewandreplytweets.Response.lstPostsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PostsServiceImpl implements PostsService{

    @Autowired
    public PostDao postDao;

    @Autowired
    PostsHibernate postsHibernate;

    @Async
    public CompletableFuture<Integer> getUserPostsCount_call(String user) {
        return CompletableFuture.supplyAsync(() -> postDao.countAllByUser(user));
    }

//    @Cacheable(key = "#usercount" , value = "count")
    public int getUserPostsCount(String usercount) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> count =  getUserPostsCount_call(usercount);
        return count.get();
    }



    @Async
    public CompletableFuture<List<lstPostsResponse>> getUserPosts_call(String user) {
        return CompletableFuture.supplyAsync(() -> postsHibernate.findAllByUserAndOrderByDateDesc(user));
    }

//    @Cacheable(key = "#postuser" , value = "lst_posts")
    public List<lstPostsResponse> findAllByUserAndOrderByDateDesc(String postuser) throws ExecutionException, InterruptedException {
        CompletableFuture<List<lstPostsResponse>> lst_posts =  getUserPosts_call(postuser);
        System.out.println(lst_posts);
        return lst_posts.get();
    }



    @Async
    public CompletableFuture<Posts> getPostsInfo_call(String postid, String user) {
        return CompletableFuture.supplyAsync(() -> postDao.findPostsByPostidAndUser(postid, user));
    }

//    @Cacheable(key = "#postid" , value = "lst_posts")
    public Posts getPostsinfo(String postid, String user) throws ExecutionException, InterruptedException {
        CompletableFuture<Posts> postsinfo =  getPostsInfo_call(postid, user);
        return postsinfo.get();
    }



    @Async
    public CompletableFuture<List<lstPostsResponse>> getPosts_call() {
        return CompletableFuture.supplyAsync(() -> postsHibernate.findAllPosts());
    }


    public List<lstPostsResponse> findAllPosts() throws ExecutionException, InterruptedException {
        CompletableFuture<List<lstPostsResponse>> lst_posts =  getPosts_call();
        System.out.println(lst_posts);
        return lst_posts.get();
    }
}
