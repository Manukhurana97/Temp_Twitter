package com.example.posttweets.Controller;

import com.example.posttweets.Dao.PostDao;
import com.example.posttweets.Models.Posts;
import com.example.posttweets.Request.PostsRequest;
import com.example.posttweets.Response.PostsResponse;
import com.example.posttweets.Service.UserService;
import com.example.posttweets.Service.UserServiceImpl;
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


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/Posts")
public class PostsController {

    @Autowired
    public PostDao postDao;

    @Autowired
    public UserService userService;

    @Autowired
    public GenerateID generateID;


    @Operation(summary = "Post a tweet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "tweeted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/Tweet")
    public ResponseEntity<PostsResponse> posttweets(@RequestHeader(value = "Authentication") String token, @RequestBody
            PostsRequest postsRequest) {
        HttpStatus status = HttpStatus.CREATED;
        PostsResponse response = new PostsResponse();

        try {
            Posts posts = new Posts();
            Map.Entry<String, String> map = userService.usercall(token);
            try {
                posts.setUser(map.getKey());

                posts.setPostDescription(postsRequest.getPostDescription());
                posts.setTags(postsRequest.getTags());
                posts.setUrl(postsRequest.getUrl());

                posts.setPostid(generateID.generatePostId(map.getKey()));

                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                posts.setDate(formatter.format(date));

                postDao.saveAndFlush(posts);
                response.setMessage("Post Successfully");
            }
            catch (Exception e)
            {
                response.setMessage(e.getMessage());
            }
        } catch (Exception e) {
            response.setMessage("Invalid User");
        }


        return new ResponseEntity<>(response, status);
    }

}
