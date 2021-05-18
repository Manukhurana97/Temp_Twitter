package com.example.viewandreplytweets.Service;

import com.example.viewandreplytweets.Models.Comments;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CommentService {

    public int getPostsCommentCounts(String commentcount) throws ExecutionException, InterruptedException;

    public List<Comments> getPostscomments(String postcommentsid) throws ExecutionException, InterruptedException;
}
