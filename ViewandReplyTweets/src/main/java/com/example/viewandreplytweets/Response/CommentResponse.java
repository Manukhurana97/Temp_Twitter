package com.example.viewandreplytweets.Response;

import com.example.viewandreplytweets.Models.Comments;
import com.example.viewandreplytweets.Models.Posts;

import java.util.List;

public class CommentResponse {

    public int status;
    public String message;
    public int postcount;
    public List<Comments> comments;

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

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
