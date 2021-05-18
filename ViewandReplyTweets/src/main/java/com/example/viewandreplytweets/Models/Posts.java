package com.example.viewandreplytweets.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Posts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String postid;
    public String user;
    @Length(max = 140)
    public String postDescription;
    public String tags;
    public String date;
    public String url;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "posts")
    public List<Comments> comments;


    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }


    public String getPostDescription() {
        return postDescription;
    }

    public String getTags() {
        return tags;
    }


    public String getDate() {
        return date;
    }



    public String getUrl() {
        return url;
    }





}
