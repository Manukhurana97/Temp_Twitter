package com.example.viewandreplytweets.Dao.Hibernate;

import com.example.viewandreplytweets.Response.lstPostsResponse;

import java.util.List;

public interface PostsHibernate {

    public List<lstPostsResponse> findAllByUserAndOrderByDateDesc(String user);
    public List<lstPostsResponse> findAllPosts();
}
