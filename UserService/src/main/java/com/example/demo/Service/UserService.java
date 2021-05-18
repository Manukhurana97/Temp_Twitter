package com.example.demo.Service;

import com.example.demo.Model.Users;

import java.util.concurrent.ExecutionException;

public interface UserService {

    public Users find_User(String username) throws ExecutionException, InterruptedException;
}
