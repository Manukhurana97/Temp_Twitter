package com.example.posttweets.Util;

import com.example.posttweets.Dao.CommentsDao;
import com.example.posttweets.Models.Comments;
import com.example.posttweets.Models.Posts;
import com.example.posttweets.Service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Random;

@Service
public class GenerateID {

    @Autowired
    PostsService postsService;

    @Autowired
    CommentsDao commentsDao;


    //    base64({A-Z0-9_/}{9})
    public synchronized String generatePostId(String userid) {
        int leftLimit = 47; // numeral '0'
        int rightLimit = 95; // letter '9'
        int targetStringLength = 9;

        String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 95))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String postid = Base64.getEncoder().withoutPadding().encodeToString(generatedString.getBytes());

        try {
            Posts posts = postsService.findByPostid(postid, userid);
            if(posts.getPostid()==null) return postid;
            else generatePostId(userid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return postid;
        }

        return postid;

    }


    //    base64({a-zA-Z0-9}{12})
    public synchronized String generateCommentId() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;

        String generatedString = new Random().ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String commentid = Base64.getEncoder().withoutPadding().encodeToString(generatedString.getBytes());

        try {
            Comments comments = commentsDao.findCommentsById(commentid);
           if (comments.getId()==null) return commentid;
           else generateCommentId();
        } catch (Exception e) {
            return commentid;
        }
        return commentid;
    }


}
