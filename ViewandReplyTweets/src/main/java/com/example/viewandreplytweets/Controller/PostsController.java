package com.example.viewandreplytweets.Controller;

import com.example.viewandreplytweets.Dao.CommentsDao;
import com.example.viewandreplytweets.Dao.Hibernate.PostsHibernate;
import com.example.viewandreplytweets.Dao.PostDao;
import com.example.viewandreplytweets.Dao.Hibernate.PostsHibernateImpl;
import com.example.viewandreplytweets.Models.Posts;
import com.example.viewandreplytweets.Response.PostResponse;
import com.example.viewandreplytweets.Response.lstPostsResponse;
import com.example.viewandreplytweets.Service.PostsService;
import com.example.viewandreplytweets.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Posts")
public class PostsController {

    @Autowired
    public PostDao postDao;

    @Autowired
    public CommentsDao commentsDao;

    @Autowired
    UserService userService;

    @Autowired
    PostsService postsService;

    @Autowired
    PostsHibernate postsHibernate;


    @GetMapping("/UserPostsCount")
    public ResponseEntity<PostResponse> getuserPostsCount(@RequestHeader(value = "Authentication") String token) {
        PostResponse response = new PostResponse();
        HttpStatus status = HttpStatus.OK;

        try {
//            call user
            Map.Entry<String, String> map = userService.usercall(token);

            try {
//           get all posts of user
                int userPostCount = postsService.getUserPostsCount(map.getKey());

                if (userPostCount == 0)
                    response.setMessage("post your first tweet");
                else
                    response.setMessage("Success");

                response.setPostcount(userPostCount);
                response.setStatus(status.value());

            } catch (Exception ee) {
                response.setMessage(ee.getMessage());
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid Token or Unauthorized");
        }

        return new ResponseEntity<>(response, status);
    }


    @PostMapping("/")
    public ResponseEntity<PostResponse> getuserPosts(@RequestHeader(value = "Authentication") String token) {
        PostResponse response = new PostResponse();
        HttpStatus status = HttpStatus.OK;
        try {
//            call user
            Map.Entry<String, String> map = userService.usercall(token);
            try {

//           get all posts
                List<lstPostsResponse> post_lst = postsService.findAllPosts();


                status = HttpStatus.OK;
                response.setLst_posts(post_lst);
                response.setStatus(status.value());

                response.setMessage("Success");
            } catch (Exception e) {
                response.setMessage(e.getMessage());
            }

        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid Token or Unauthorized");
        }

        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/UserPosts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestHeader(value = "Authentication") String token) {
        PostResponse response = new PostResponse();
        HttpStatus status = HttpStatus.OK;
        try {
//            call user
            Map.Entry<String, String> map = userService.usercall(token);
            try {

//           get all posts of user
                List<lstPostsResponse> post_lst = postsService.findAllByUserAndOrderByDateDesc(map.getKey());

                if (post_lst.size() == 0)
                    response.setMessage("User has not tweeted any posts");
                else
                    response.setMessage("Success");

                status = HttpStatus.OK;
                response.setLst_posts(post_lst);
                response.setStatus(status.value());

                response.setMessage("Success");
            } catch (Exception e) {
                response.setMessage(e.getMessage());
            }

        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid Token or Unauthorized");
        }

        return new ResponseEntity<>(response, status);
    }


    @PostMapping("/Postinfo")
    public ResponseEntity<PostResponse> getPostsInfo(@RequestHeader(value = "Authentication") String token,
                                                     @RequestBody Posts postsRequest) {
        PostResponse response = new PostResponse();
        HttpStatus status = HttpStatus.OK;
        try {
//            call user
            Map.Entry<String, String> map = userService.usercall(token);
            try {
//           get all posts of user
                Posts post_info = postsService.getPostsinfo(postsRequest.getPostid(), map.getKey());

                if (post_info == null)
                    throw new NullPointerException("Invalid tweet Id/no tweet found");


                status = HttpStatus.OK;
                response.setPosts(post_info);
                response.setStatus(status.value());
                response.setMessage("Success");

            } catch (Exception e) {
                response.setMessage(e.getMessage());
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid Token or Unauthorized");
        }

        return new ResponseEntity<>(response, status);
    }

}
