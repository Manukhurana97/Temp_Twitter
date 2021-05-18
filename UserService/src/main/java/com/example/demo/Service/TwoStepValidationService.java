package com.example.demo.Service;

import com.example.demo.Model.TwoStepVerification;

import javax.validation.constraints.Email;
import java.util.concurrent.ExecutionException;

public interface TwoStepValidationService {

    public TwoStepVerification findTwoStepVerificationByUser_UsernameAndValidationtoken(String user_username, String validationtoken) throws ExecutionException, InterruptedException;
}
