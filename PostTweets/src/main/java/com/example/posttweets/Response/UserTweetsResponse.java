package com.example.posttweets.Response;

import com.example.posttweets.Models.Posts;

import java.util.List;

public class UserTweetsResponse {
    public int status;
    public String message;
    public List<Posts> User_tweets;

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

    public List<Posts> getUser_tweets() {
        return User_tweets;
    }

    public void setUser_tweets(List<Posts> user_tweets) {
        User_tweets = user_tweets;
    }
}
