package com.example.demo.Service;

import com.example.demo.Model.UserDetails;

import java.util.concurrent.ExecutionException;

public interface UserDetailsService {

    public UserDetails find_Userdetails(String username) throws ExecutionException, InterruptedException;
}
