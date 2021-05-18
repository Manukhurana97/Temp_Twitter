package com.example.demo.Service;

import com.example.demo.Dao.AuthorityDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Dao.userdetailsDao;
import com.example.demo.Model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public userdetailsDao userdetailsdao;

    @Autowired
    public UserDao userdao;

    @Autowired
    public AuthorityDao authorityDao;



    @Async
     public CompletableFuture<UserDetails> find_Userdetails_call(String username) {
         return CompletableFuture.supplyAsync(() -> userdetailsdao.findByusers_Username(username));

     }

    public UserDetails find_Userdetails(String username) throws ExecutionException, InterruptedException {
        CompletableFuture<UserDetails> CF_userDetails = find_Userdetails_call(username);
        return CF_userDetails.get();
    }




}
