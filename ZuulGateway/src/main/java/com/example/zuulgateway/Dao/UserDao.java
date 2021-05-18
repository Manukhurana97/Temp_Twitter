package com.example.zuulgateway.Dao;

import com.example.zuulgateway.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@EnableTransactionManagement
public interface UserDao extends JpaRepository<Users, String> {
    public Users findByUsername(String username);


}
