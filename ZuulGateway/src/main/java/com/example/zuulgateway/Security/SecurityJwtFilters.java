package com.example.zuulgateway.Security;

import com.example.zuulgateway.Dao.UserDao;
import com.example.zuulgateway.Jwt.Jwt;
import com.example.zuulgateway.Model.Users;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityJwtFilters extends OncePerRequestFilter {

    @Autowired
    private Jwt jwt;

    @Autowired
    private UserDao dao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String jwttoken = request.getHeader("Authentication");
        try {
            System.out.println(jwttoken);
            if (jwt.checkToken(jwttoken) != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Claims claims = jwt.checkToken(jwttoken);
                System.out.println("claims "+ claims);
                Users users = dao.findByUsername(claims.get("SESS_USERNAME").toString());
                System.out.println(users.getUsername());
                if (!jwt.isTokenExpired(jwttoken)) {
                    List<GrantedAuthority> authorities = new ArrayList<>();

                    authorities.add(new SimpleGrantedAuthority(users.getAuthorities().getAuthority()));

                    UserDetails userDetails = new User(users.getUsername(), users.getPassword(), authorities);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    System.out.println(usernamePasswordAuthenticationToken);
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
