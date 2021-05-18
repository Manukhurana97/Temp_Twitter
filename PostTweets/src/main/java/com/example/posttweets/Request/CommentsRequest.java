package com.example.posttweets.Request;

import com.example.posttweets.Models.Posts;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;


public class CommentsRequest {

    public String parent_comment_id;
    public String comment_text;
    public Posts posts;

    public String getParent_comment_id() {
        return parent_comment_id;
    }

    public void setParent_comment_id(String parent_comment_id) {
        this.parent_comment_id = parent_comment_id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }


    @Override
    public String toString() {
        return "CommentsRequest{" +
                "parent_comment_id='" + parent_comment_id + '\'' +
                ", comment_text='" + comment_text + '\'' +
                ", posts=" + posts +
                '}';
    }
}
