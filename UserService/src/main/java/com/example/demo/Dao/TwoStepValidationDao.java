package com.example.demo.Dao;

import com.example.demo.Model.TwoStepVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.validation.constraints.Email;

@EnableTransactionManagement
public interface TwoStepValidationDao extends JpaRepository<TwoStepVerification, Integer> {

    public TwoStepVerification findTwoStepVerificationByUser_UsernameAndValidationtoken(@Email String user_username, String validationtoken);
}
