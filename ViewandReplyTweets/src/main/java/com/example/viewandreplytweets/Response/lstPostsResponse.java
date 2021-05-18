package com.example.viewandreplytweets.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigInteger;

public class lstPostsResponse implements Serializable {

    public String postid;
    public String date;
    public String postDescription;
    public String tags;
    public String url;
    public String user;
    public BigInteger commentcount;

    public lstPostsResponse() {
    }

    public lstPostsResponse(String postid, String date, String postDescription, String tags, String url, String user, BigInteger commentcount) {
        this.postid = postid;
        this.date = date;
        this.postDescription = postDescription;
        this.tags = tags;
        this.url = url;
        this.user = user;
        this.commentcount = commentcount;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigInteger getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(BigInteger commentcount) {
        this.commentcount = commentcount;
    }

    @Override
    public String toString() {
        return "lstPostsResponse{" +
                "postid='" + postid + '\'' +
                ", user='" + user + '\'' +
                ", postDescription='" + postDescription + '\'' +
                ", tags='" + tags + '\'' +
                ", date='" + date + '\'' +
                ", url='" + url + '\'' +
                ", commentcount='" + commentcount + '\'' +
                '}';
    }
}
