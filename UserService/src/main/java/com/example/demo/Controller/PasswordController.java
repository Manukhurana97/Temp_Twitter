package com.example.demo.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Base64;

import com.example.demo.Email.EmailServiceImpl;
import com.example.demo.Service.PasswordService;
import com.example.demo.Service.UserService;
import com.example.demo.Model.Email;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.UserDao;
import com.example.demo.Dao.passwordresetDao;
import com.example.demo.Jwt.Jwt;
import com.example.demo.Request.RegisterForm;
import com.example.demo.Response.RegisterResponse;
import com.example.demo.Validator.PasswordValidator;
import com.example.demo.Model.PasswordResetToken;
import com.example.demo.Model.Users;
import io.jsonwebtoken.Claims;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Password")
@RestController
public class PasswordController {

//	logging
//	Logger loging  = LoggerFactory.getLogger("password");

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public JdbcUserDetailsManager detailsManager;

    @Autowired
    public UserDao dao;

    @Autowired
    public passwordresetDao passworddao;

    @Autowired
    public Jwt jwt;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    public UserService userService;

    @Autowired
    PasswordService passwordService;

    @Operation(summary = "Change password when user login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Password Changed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/change-password")
    public ResponseEntity<RegisterResponse> changepassword(@RequestHeader(value = "Authentication") String token, @RequestBody RegisterForm form) {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.CREATED;
//	     password format validation
        try {

            if (PasswordValidator.isValid(form.getPassword())) {
                String newencodedPassword = passwordEncoder.encode(form.getPassword());
                Claims claims = jwt.checkToken(token);

                Users user = userService.find_User(claims.get("SESS_USERNAME").toString());

//	         password similarity
                if (passwordEncoder.matches(form.getPassword(), user.getPassword())) {
                    status = HttpStatus.CONFLICT;
                    throw new IllegalArgumentException("Old and New Password can't be same");
                } else {
                    user.setPassword(newencodedPassword);
                    dao.saveAndFlush(user);

//		    	 send email
                    String body = "This email is to informed you that your password has been changed successfully";
                    Email email = new Email(user.getUsername(), "Password Reset", 0, body);
                    emailService.SendEmail(email);
                    response.setMessage(" Password change successfully");
                }
            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(response, status);
    }


    @Operation(summary = "Change password request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Changed password request successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/forgot-password")
    public ResponseEntity<RegisterResponse> forgotpassword(@RequestBody RegisterForm form) {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.OK;
//	     password format validation

        if (form.getUsername().isEmpty() || form.getUsername().equals("")) {
            response.setMessage("email cant be blank");
        } else {
            try {

                Users user = userService.find_User(form.getUsername());
                if (user.getUsername() == null) {
                    throw new UsernameNotFoundException("invalid Email");
                } else {
                    String token = UUID.randomUUID().toString()+System.currentTimeMillis();
                    token = token.substring(token.length()-25);
                    String encryptpasswordtoken = Base64.getEncoder().withoutPadding().encodeToString(token.getBytes());
                    PasswordResetToken pass = new PasswordResetToken();
                    pass.setUser(user);
                    pass.setToken(encryptpasswordtoken);
                    pass.setIs_used(false);
                    pass.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 31));
                    passworddao.saveAndFlush(pass);

                    //	    		 send email
                    String body = "This mail is in response to a request to recover a forgotten password" +
                            "\nhttp://localhost:4200/ForgotPasswordConform/" + encryptpasswordtoken +
                            "\n This link is valid for 30 minutes" +
                            "\n Please do not reply on this mail";

                    Email email = new Email(form.getUsername(), "Password Reset", 1000 * 30, body);
                    emailService.SendEmail(email);
                    response.setMessage("Mail send successfully");
                }
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
                response.setMessage("Invalid Email");
            }
        }
        return new ResponseEntity<>(response, status);
    }


    @Operation(summary = "Change password token verification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Changed password request successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/reset-password/{resettoken}")
    public ResponseEntity<RegisterResponse> resetpasswordtokenverify(@PathVariable(name = "resettoken") String resettoken) {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.OK;
//	     password format validation

        try {

            PasswordResetToken password_reset_token = passwordService.findByToken(resettoken);

            if (!password_reset_token.getExpiryDate().after(new Date(System.currentTimeMillis()))) {
                response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
                throw new UsernameNotFoundException("Link is invalid");
            } else if (password_reset_token.getIs_used()) {
                response.setStatus(HttpStatus.CONFLICT.value());
                throw new UsernameNotFoundException("Link already used");
            } else {
                response.setMessage("password_reset_token is valid ");

            }
        } catch (Exception e) {

            status = HttpStatus.UNPROCESSABLE_ENTITY;
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }

    @Operation(summary = "Change password with token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Changed password request successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Old and New Password can't be same"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/reset-password-confirm/{resettoken}")
    public ResponseEntity<RegisterResponse> resetpasswordyconfirm(@PathVariable(name = "resettoken") String resettoken, @RequestBody RegisterForm form) {

        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.OK;
        Date date = new Date();
//	     password format validation

        if (resettoken.isEmpty()) {
            response.setMessage("password token can't be blank");
        } else {
            try {

                PasswordResetToken password_reset_token = passwordService.findByToken(resettoken);
                if (!password_reset_token.getExpiryDate().after(new Date(System.currentTimeMillis()))) {
                    response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
                    throw new UsernameNotFoundException("Link expired");
                } else if (password_reset_token.getIs_used()) {

                    response.setStatus(HttpStatus.CONFLICT.value());
                    throw new UsernameNotFoundException("Link is already used");
                } else { if (PasswordValidator.isValid(form.getPassword())) {
                        String newencodedPassword = passwordEncoder.encode(form.getPassword()); // encode password
                        Users user = userService.find_User(password_reset_token.getUser().getUsername()); // find the user
                        user.setPassword(newencodedPassword); // set updated password
                        dao.saveAndFlush(user);  // save updated password

//                        update token (is used = true)
                        password_reset_token.setIs_used(true);  // test token to used
                        passworddao.saveAndFlush(password_reset_token); // change password status


//			    		 send email
                        String body = "Your password for the " + user.getUsername() + "  has been changed" +
                                "\nwe are sending you this fot your protection" +
                                "\nDate of password change " + date.getDate();
                        Email email = new Email(form.getUsername(), "Confirmation of passsword change", 0, body);
                        emailService.SendEmail(email);
                        response.setMessage("Password change successsfully");
                    } else {
                        response.setMessage("Invalid password format");
                    }
                }
            } catch (Exception e) {
                status = HttpStatus.NOT_FOUND;
                response.setMessage(e.getMessage());
            }
        }
        return new ResponseEntity<>(response, status);
    }


    @PostMapping("/CheckTokenpasswordencoder")
    public Map<String, String> getuserfromtoken(@RequestHeader(name = "Authentication") String token, @RequestBody String password) {
        Map<String, String> map = new HashMap<>();
        Claims claims;
        try {
            claims = jwt.checkToken(token);

            Users user = userService.find_User(claims.get("SESS_USERNAME").toString()); // user
            String newencodedPassword = passwordEncoder.encode(password); // encoded password
            if (passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException(" pasword can't be same  ");
            }
            map.put(user.getUsername(), newencodedPassword);
            return map;
        } catch (Exception e) {
            map.put(e.toString(), e.toString());
            return map;
        }

    }

}
