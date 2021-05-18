package com.example.viewandreplytweets.Response;

import com.example.viewandreplytweets.Models.Posts;

import java.util.List;

public class PostResponse {

    public int status;
    public String message;
    public int postcount;
    public Posts posts;
    public List<lstPostsResponse> lst_posts;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPostcount() {
        return postcount;
    }

    public void setPostcount(int postcount) {
        this.postcount = postcount;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public List<lstPostsResponse> getLst_posts() {
        return lst_posts;
    }

    public void setLst_posts(List<lstPostsResponse> lst_posts) {
        this.lst_posts = lst_posts;
    }
}
