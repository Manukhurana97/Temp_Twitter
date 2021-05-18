package com.example.demo.Service;

import com.example.demo.Model.PasswordResetToken;

import java.util.concurrent.ExecutionException;

public interface PasswordService {

    public PasswordResetToken findByToken(String token) throws ExecutionException, InterruptedException;

}
