package com.example.viewandreplytweets.Controller;

import com.example.viewandreplytweets.Dao.CommentsDao;
import com.example.viewandreplytweets.Dao.PostDao;
import com.example.viewandreplytweets.Models.Comments;
import com.example.viewandreplytweets.Models.Posts;
import com.example.viewandreplytweets.Response.CommentResponse;
import com.example.viewandreplytweets.Service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Comments")
public class CommandController {

    @Autowired
    public PostDao postDao;

    @Autowired
    public CommentsDao commentsDao;

    @Autowired
    UserService userService;

    @Autowired
    PostsService postsService;

    @Autowired
    CommentService commentsService;



    @Operation(summary = "Comments count of a posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment count of a posts"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @GetMapping("/PostsCommentCounts")
    public ResponseEntity<CommentResponse> getPostsCommentCounts(@RequestHeader(value = "Authentication") String token,
                                                                 @RequestBody Posts postsrequest) {
        CommentResponse response = new CommentResponse();
        HttpStatus status = HttpStatus.OK;
        try {
//            call user
            Map.Entry<String, String> map = userService.usercall(token);
            try {

//              find posts
                Posts posts = postsService.getPostsinfo(postsrequest.getPostid(), map.getKey());

                if(posts == null)
                    throw new NullPointerException("Invalid tweet Id/no tweet found");

//               counts all comments
                int PostCommentCount = commentsService.getPostsCommentCounts(posts.getPostid());

                if (PostCommentCount==0)
                    response.setMessage("Be the first to comment");
                else
                    response.setMessage("Success");


                response.setPostcount(PostCommentCount);
                response.setStatus(status.value());

            } catch (Exception ee) {
                response.setMessage(ee.getMessage());
            }
        } catch (Exception e) {
            response.setMessage("Invalid Token or Unauthorized");
        }

        return new ResponseEntity<>(response, status);
    }


    @Operation(summary = "Comments of a posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment of a posts"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/PostComments")
    public ResponseEntity<CommentResponse> getPostComments(@RequestHeader(value = "Authentication") String token,
                                                           @RequestBody Posts postsrequest) {
        CommentResponse response = new CommentResponse();
        HttpStatus status = HttpStatus.OK;
        try {
//            call user
            Map.Entry<String, String> map = userService.usercall(token);
            try {
                System.out.println(map.getKey());
//                find posts
                Posts posts = postsService.getPostsinfo(postsrequest.getPostid(), map.getKey());

                if(posts.getPostid() == null)
                    throw new NullPointerException("Invalid post id");

//              find all comments
                List<Comments> comments_lst = commentsService.getPostscomments(posts.getPostid());
                System.out.println("size "+comments_lst.size() );
                if (comments_lst.size()==0)
                    response.setMessage("Be the first to comment");
                else
                    response.setMessage("Success");

                response.setComments(comments_lst);
                response.setStatus(status.value());

            } catch (Exception ee) {
                response.setMessage(ee.getMessage());
            }

        } catch (Exception e) {
            response.setMessage("Invalid Token or Unauthorized");
        }

        return new ResponseEntity<>(response, status);
    }
}
