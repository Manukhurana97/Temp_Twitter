package com.example.posttweets.Dao;

import com.example.posttweets.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public interface CommentsDao extends JpaRepository<Comments, String> {

    public Comments findCommentsById(String commentid);
}
