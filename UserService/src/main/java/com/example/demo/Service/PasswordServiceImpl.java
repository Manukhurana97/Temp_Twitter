package com.example.demo.Service;

import com.example.demo.Dao.passwordresetDao;
import com.example.demo.Model.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PasswordServiceImpl implements PasswordService{

    @Autowired
    public passwordresetDao passworddao;

    public CompletableFuture<PasswordResetToken> findByToken_call(String token){
        return CompletableFuture.supplyAsync( ()->passworddao.findByToken(token) );
    }

    @Override
    public PasswordResetToken findByToken(String token) throws ExecutionException, InterruptedException {
        CompletableFuture<PasswordResetToken> passwordResetTokenCompletableFuture = findByToken_call(token);
        return passwordResetTokenCompletableFuture.get();
    }
}
