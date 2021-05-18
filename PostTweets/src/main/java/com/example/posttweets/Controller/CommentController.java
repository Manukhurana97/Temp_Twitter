package com.example.posttweets.Controller;


import com.example.posttweets.Dao.CommentsDao;
import com.example.posttweets.Dao.PostDao;
import com.example.posttweets.Models.Comments;
import com.example.posttweets.Models.Posts;
import com.example.posttweets.Request.CommentsRequest;
import com.example.posttweets.Response.CommentResponse;
import com.example.posttweets.Service.PostsService;
import com.example.posttweets.Service.UserService;
import com.example.posttweets.Util.GenerateID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Comments")
public class CommentController {

    @Autowired
    public PostDao postDao;

    @Autowired
    public CommentsDao commentsDao;

    @Autowired
    public UserService userService;

    @Autowired
    public PostsService postsService;

    @Autowired
    public GenerateID generateID;


    @Operation(summary = "post a comment on a tweet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Commented successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/Post")
    public ResponseEntity<CommentResponse> postcomment(@RequestHeader(value = "Authentication") String token,
                                                       @RequestBody CommentsRequest commentsRequest) {
        CommentResponse response = new CommentResponse();
        HttpStatus status = HttpStatus.CREATED;
        try{
            Comments comments = new Comments();

//            calling user
            Map.Entry<String, String> map = userService.usercall(token);
            try {
                comments.setUser(map.getKey());
                System.out.println(map.getKey());
                System.out.println(commentsRequest.toString());
                System.out.println(commentsRequest.getPosts().getPostid());
                System.out.println(commentsRequest.getPosts().toString());

//            calling posts
//                Posts post = postsService.findByPostid(commentsRequest.getPosts().getPostid(), map.getKey());
                Posts post = postDao.findPostsByPostidAndUser(commentsRequest.getPosts().getPostid(), map.getKey());
                System.out.println(post.getPostid());
                System.out.println("test "+ post.toString());
                System.out.println(post.getPostid()==null);
                if(post.getPostid()==null) throw new NoSuchElementException("Invalid post id");
                comments.setPosts(post);

//            set comment text
                comments.setComment_text(commentsRequest.getComment_text());

//          set parent comment id
                comments.setParent_comment_id(commentsRequest.getParent_comment_id());

//            set id
                comments.setId(generateID.generateCommentId());

//            adding date

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                comments.setDate(formatter.format(date));

                commentsDao.saveAndFlush(comments);
                response.setStatus(status.value());
                response.setMessage("Comment post successfully");
            }
            catch(Exception e) {
                response.setStatus(HttpStatus.NO_CONTENT.value());
                response.setMessage("Invalid post Id");
            }
        }
        catch(Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage("Invalid user token");
        }
        return new ResponseEntity<>(response, status);
    }

}
