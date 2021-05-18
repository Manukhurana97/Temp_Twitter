package com.example.viewandreplytweets.Dao;


import com.example.viewandreplytweets.Models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
public interface PostDao extends JpaRepository<Posts, String> {


    public Posts findByPostid(String Id);
    public Posts findPostsByPostidAndUser(String postid, String User);
    public int countAllByUser(String user);
}
