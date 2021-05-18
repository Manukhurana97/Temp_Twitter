package com.example.posttweets.Dao;

import com.example.posttweets.Models.Posts;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public interface PostDao extends JpaRepository<Posts, String> {
    @Query(value = "select * from posts where postid=?1 and user=?2;")
    public Posts findPostsByPostidAndUser(String postid, String User);

}
