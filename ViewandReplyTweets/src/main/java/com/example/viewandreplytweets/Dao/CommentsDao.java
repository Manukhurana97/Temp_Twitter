package com.example.viewandreplytweets.Dao;


import com.example.viewandreplytweets.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableTransactionManagement
public interface CommentsDao extends JpaRepository<Comments, String> {

    public int countAllByPosts_Postid(String postid);

    @Query(value = "select * from comments where postid=?;", nativeQuery = true)
    public List<Comments> findCommentsByPosts_Postid(String postid);

}
