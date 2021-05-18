package com.example.viewandreplytweets.Service;

import com.example.viewandreplytweets.Dao.CommentsDao;
import com.example.viewandreplytweets.Dao.PostDao;
import com.example.viewandreplytweets.Models.Comments;
import com.example.viewandreplytweets.Models.Posts;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CommentsServiceImpl implements CommentService {

    @Autowired
    public PostDao postDao;

    @Autowired
    public CommentsDao commentsDao;

    @Async
    public CompletableFuture<Integer> getPostsCommentCounts_call(String postid) {
        return CompletableFuture.supplyAsync(() -> commentsDao.countAllByPosts_Postid(postid));
    }

    @Cacheable(key = "#commentcount" , value = "count")
    public int getPostsCommentCounts(String commentcount) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> count =  getPostsCommentCounts_call(commentcount);
        return count.get();
    }



    @Async
    public CompletableFuture<List<Comments>> getPostscomments_call(String postid) {
        return CompletableFuture.supplyAsync(() -> commentsDao.findCommentsByPosts_Postid(postid));
    }

//    @Cacheable(key = "#postcommentsid" , value = "lst_posts")
    public List<Comments> getPostscomments(String postcommentsid) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Comments>> lst_comments =  getPostscomments_call(postcommentsid);
        return lst_comments.get();
    }




}
