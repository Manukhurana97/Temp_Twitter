package com.example.demo.Service;

import com.example.demo.Dao.AuthorityDao;
import com.example.demo.Dao.UserDao;
import com.example.demo.Dao.userdetailsDao;
import com.example.demo.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public userdetailsDao userdetailsdao;

    @Autowired
    public UserDao userdao;

    @Autowired
    public AuthorityDao authorityDao;



    @Async
    public CompletableFuture<Users> find_User_call(String username)  {
        return  CompletableFuture.supplyAsync( () ->  userdao.findByUsername(username));
    }

    public Users find_User(String username) throws ExecutionException, InterruptedException {
        CompletableFuture<Users>  CF_user = find_User_call(username);
        return CF_user.get();
    }






}
