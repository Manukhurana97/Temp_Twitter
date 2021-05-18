package com.example.demo.Controller;

import com.example.demo.Dao.TwoStepValidationDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Dao.userdetailsDao;
import com.example.demo.Email.EmailServiceImpl;
import com.example.demo.Extension.ExtendedUser;
import com.example.demo.Jwt.Jwt;
import com.example.demo.Model.Email;
import com.example.demo.Model.TwoStepVerification;
import com.example.demo.Model.UserDetails;
import com.example.demo.Model.Users;
import com.example.demo.Request.LoginForm;
import com.example.demo.Request.RegisterForm;
import com.example.demo.Response.RegisterResponse;
import com.example.demo.Service.PasswordService;
import com.example.demo.Service.TwoStepValidationService;
import com.example.demo.Service.UserDetailsService;
import com.example.demo.Service.UserService;
import com.example.demo.Thread.SpringAsyncConfig;
import com.example.demo.Validator.EmailFormatValidator;
import com.example.demo.Validator.PasswordValidator;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.activity.InvalidActivityException;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ExecutionException;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Account")
@RestController
public class Controller {


//	logging
//	Logger loging  = LoggerFactory.getLogger("user");

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public JdbcUserDetailsManager detailsManager;

    @Autowired
    public AuthenticationManager authentication;

    @Autowired
    public Jwt jwt;

    @Autowired
    public UserDao userdao;

    @Autowired
    public UserService UserServiceImpl;

    @Autowired
    public userdetailsDao userdetailsdao;

    @Autowired
    public TwoStepValidationDao twoStepValidationDao;

    @Autowired
    public SpringAsyncConfig springAsyncConfig;


    @Autowired
    public UserService userService;
    @Autowired
    public UserDetailsService userDetailsServiceImpl;
    @Autowired
    PasswordService passwordService;
    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    TwoStepValidationService twoStepValidationService;


    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account create successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Email already exists"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/Register")
    public ResponseEntity<RegisterResponse> Register(@Valid @RequestBody RegisterForm form) {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.OK;
        UserDetails u_details = new UserDetails();
        try {
            List<GrantedAuthority> authority = new ArrayList<>();
            authority.add(new SimpleGrantedAuthority("ROLE_USER"));
//	        password format validation

            if (EmailFormatValidator.isValid(form.getUsername())) {
                if (PasswordValidator.isValid(form.getPassword())) {
                    String encodedPassword = passwordEncoder.encode(form.getPassword());
//                    User detail = new User(form.getUsername(), encodedPassword, true,true, true, true, authority);
                    ExtendedUser detail = new ExtendedUser(form.getUsername(), encodedPassword, true, true, true, true, authority);

                    detailsManager.createUser(detail);

//                    user details
                    u_details.setFirstname(form.getFirstname());
                    u_details.setLastname(form.getLastname());
                    u_details.setContactno(form.getContactno());
                    u_details.setUserCountry(form.country);
                    u_details.setUsers(userService.find_User(form.getUsername()));
                    userdetailsdao.saveAndFlush(u_details);

                    response.setMessage("User created successfully");
                } else {
                    status = HttpStatus.BAD_REQUEST;
                    response.setMessage("password format is invalid, It must have one Capital letter, small letters and one special char");
                }
            } else {
                status = HttpStatus.BAD_REQUEST;
                response.setMessage("Email format is invalid, it must follow stand email format");
            }
        } catch (Exception e) {
            status = HttpStatus.CONFLICT;
            System.out.print(e.getMessage());
            response.setMessage("email associated with different account");
        }

        return new ResponseEntity<>(response, status);
    }


    @Operation(summary = "Register Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account create successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Email already exists"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/AuthorityRegister")
    public ResponseEntity<RegisterResponse> AuthorityRegister(@RequestHeader(value = "Authentication") String token, @Valid @RequestBody RegisterForm form) {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.CREATED;
        Claims claims;

        try {
            claims = jwt.checkToken(token);

            Users user = UserServiceImpl.find_User(claims.get("SESS_USERNAME").toString());

            String auth = user.getAuthorities().getAuthority();
            if (!auth.equals("ROLE_ADMIN")) {
                throw new Exception("You don't have Authority to perform this operations");
            }

            try {
                List<GrantedAuthority> authority = new ArrayList<>();
                authority.add(new SimpleGrantedAuthority(form.getRole()));

//	        password format validation
                if (EmailFormatValidator.isValid(form.getUsername())) {

                    if (PasswordValidator.isValid(form.getPassword())) {
                        String encodedPassword = passwordEncoder.encode(form.getPassword());
                        //User detail = new User(form.getUsername(), encodedPassword, true,true, true, true, authority);
                        ExtendedUser detail = new ExtendedUser(form.getUsername(), encodedPassword, true, true, true, true, authority);
                        detailsManager.createUser(detail);

//                    user details
                        UserDetails u_details = new UserDetails();
                        u_details.setFirstname(form.getFirstname());
                        u_details.setLastname(form.getLastname());
                        u_details.setContactno(form.getContactno());
                        u_details.setUserCountry(form.country);
                        u_details.setUsers(userService.find_User(form.getUsername()));
                        userdetailsdao.saveAndFlush(u_details);

                        response.setMessage("Account created successfully");
                    } else {
                        status = HttpStatus.BAD_REQUEST;
                        response.setMessage("password format is invalid");
                    }
                } else {
                    status = HttpStatus.BAD_REQUEST;
                    response.setMessage("Email format is invalid, it must follow stand email format");
                }
            } catch (Exception e) {
                status = HttpStatus.CONFLICT;
                response.setMessage("email associated with different account");
            }
        } catch (Exception e) {
            status = HttpStatus.UNAUTHORIZED;
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, status);
    }


    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "201", description = "Invalid username or password"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/Login")
    public ResponseEntity<RegisterResponse> Login(@RequestBody LoginForm form) {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.OK;

        Authentication details = null;
        String token = null;
        try {
            if (EmailFormatValidator.isValid(form.getUsername())) {
                details = authentication.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword()));
                Users user = userService.find_User(details.getName());
                token = jwt.create_Token(details);
                if (user.isTwo_step_verification()) {
                    TwoStepVerification twoStepVerification = new TwoStepVerification();

                    twoStepVerification.setExpiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 31));
                    twoStepVerification.setJwttoken(token);
                    twoStepVerification.setUser(user);
                    twoStepVerification.setIs_used(false);

                    Random rnd = new Random();
                    int number = rnd.nextInt(999999);
                    String verficationtoken = String.format("%06d", number);
                    twoStepVerification.setValidationtoken(verficationtoken);
                    twoStepValidationDao.saveAndFlush(twoStepVerification);

                    Email email = new Email();
                    email.setTo(user.getUsername());
                    email.setTime(30);
                    email.setSubject("Verification token");
                    String verificationTokenBody = "your verification token " + verficationtoken;
                    email.setBody(verificationTokenBody);
                    emailService.SendEmail(email);

                    response.setStatus(HttpStatus.OK.value());
                    response.setMessage("Email sent to your registered id for validation");
                    response.setUsername(details.getName());
                } else {
                    response.setToken(token);
                    response.setStatus(HttpStatus.OK.value());
                    response.setUsername(details.getName());
                    response.setMessage("Login successfully");
                }


            } else {
                throw new InvalidActivityException("Invalid email format");
            }
        } catch (Exception e) {
            status = HttpStatus.UNAUTHORIZED;
            response.setMessage(e.getMessage());
        }
        return new ResponseEntity<>(response, status);
    }


    @PostMapping("/2MFA")
    public ResponseEntity<RegisterResponse> twoStepVerification(@RequestBody LoginForm form) throws ExecutionException, InterruptedException {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.OK;

        Authentication details = null;
        String token = null;
        try {

            TwoStepVerification mfa = twoStepValidationService.findTwoStepVerificationByUser_UsernameAndValidationtoken(form.getUsername(), form.getTwoStepVerificationToken());

            if (!mfa.getExpiryDate().after(new Date(System.currentTimeMillis())))
                throw new UsernameNotFoundException("Link is expired");
            else if (mfa.getIs_used())
                throw new UsernameNotFoundException("Token is already used");
            else {
                response.setToken(mfa.getJwttoken());
                response.setStatus(HttpStatus.OK.value());
                response.setUsername(mfa.getUser().getUsername());
                response.setMessage("Login successfully");

//                update the 2 step verification to is used
                mfa.setIs_used(true);
                twoStepValidationDao.saveAndFlush(mfa);
            }


        } catch (Exception e) {
            status = HttpStatus.UNAUTHORIZED;
            response.setMessage(e.getMessage());
        }

        return new ResponseEntity<>(response, status);

    }


    @PostMapping("/CheckToken")
    public Map<String, String> getuserfromtoken(@RequestHeader(name = "Authentication") String token) {
        Map<String, String> map = new HashMap<>();
        Claims claims;
        try {
            claims = jwt.checkToken(token);
            Users user = UserServiceImpl.find_User(claims.get("SESS_USERNAME").toString());
            map.put(user.getUsername(), user.getAuthorities().getAuthority());

        } catch (Exception e) {
            map.put("error", e.toString());
        }
        return map;
    }

    @Operation(summary = "Check token for wek request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Email already exists"),
            @ApiResponse(responseCode = "500", description = "Interval server error"),
    })
    @PostMapping("/CheckTokenWeb")
    public ResponseEntity<RegisterResponse> getuserfromtokenforweb(@RequestHeader(name = "Authentication") String token) throws AuthenticationException {
        RegisterResponse response = new RegisterResponse();
        HttpStatus status = HttpStatus.OK;

        Claims claims;
        try {

            claims = jwt.checkToken(token);

            Users user = UserServiceImpl.find_User(claims.get("SESS_USERNAME").toString());
            response.setMessage("true");
            response.setToken(token);
            response.setStatus(HttpStatus.OK.value());
            response.setUsername(user.getUsername());

        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new AuthenticationException("Invalid token");
        }
        return new ResponseEntity<>(response, status);
    }


}
