package com.example.demo.Service;

import com.example.demo.Dao.TwoStepValidationDao;
import com.example.demo.Model.TwoStepVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class TwoStepValidationServiceImpl implements TwoStepValidationService{

    @Autowired
    public TwoStepValidationDao twoStepValidationDao;

    @Async
    public CompletableFuture<TwoStepVerification> findTwoStepVerificationByUser_UsernameAndValidationtoken_call(String user_username, String validationtoken) {
        return CompletableFuture.supplyAsync( () -> twoStepValidationDao.findTwoStepVerificationByUser_UsernameAndValidationtoken(user_username, validationtoken));
    }

    @Override
    public TwoStepVerification findTwoStepVerificationByUser_UsernameAndValidationtoken(String user_username, String validationtoken) throws ExecutionException, InterruptedException {
        CompletableFuture<TwoStepVerification> completableFuture = findTwoStepVerificationByUser_UsernameAndValidationtoken_call(user_username, validationtoken);
        return completableFuture.get();
    }
}
