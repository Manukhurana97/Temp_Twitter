package com.example.demo.Controller;


import com.example.demo.Jwt.Jwt;
import com.example.demo.Response.UserDetailsResponse;
import com.example.demo.Service.PasswordService;
import com.example.demo.Service.UserDetailsService;
import com.example.demo.Service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Model.UserDetails;
import com.example.demo.Model.Users;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Userdetails")
public class UserDetailsController {

//	logging
//	Logger loging  = LoggerFactory.getLogger("details");

	@Autowired
	public Jwt jwt;


	@Autowired
	public UserService userService;

	@Autowired
	PasswordService passwordService;

	@Autowired
	public UserDetailsService userDetailsServiceImpl;



    @Autowired
    public JdbcUserDetailsManager detailsManager;


	@Operation(summary = "Change user role to from user to Artist")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
			@ApiResponse(responseCode = "500", description = "Interval server error"),
	})
    @PostMapping("/")
    public ResponseEntity<UserDetailsResponse> getusrdetails(@RequestHeader(name = "Authentication") String token) throws Exception {
		UserDetailsResponse response = new UserDetailsResponse();
    	HttpStatus status = HttpStatus.OK;
		Claims claims=null;
		try {
			claims = jwt.checkToken(token);
			Users user = userService.find_User(claims.get("SESS_USERNAME").toString());
			UserDetails user_details = userDetailsServiceImpl.find_Userdetails(user.getUsername());
			response.setUsername(user.getUsername());
			response.setFirstname(user_details.getFirstname());
			response.setLastname(user_details.getLastname());
			response.setContactno(user_details.getContactno());
			response.setMessage("Success");
			
		} catch (Exception e) {
			status = HttpStatus.UNAUTHORIZED;
			response.setMessage(e.getMessage()); }
		return new ResponseEntity<>(response ,status);
    }


}
