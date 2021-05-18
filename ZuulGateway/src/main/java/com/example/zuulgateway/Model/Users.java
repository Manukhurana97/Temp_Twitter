package com.example.zuulgateway.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity
public class Users {

    @Id
    public String username;

    private String password;


    public boolean enabled;


    public boolean accountNonExpired;


    public boolean accountNonLocked;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private Authorities authorities;


    public boolean two_step_verification;


    @Column(columnDefinition = "boolean default false")
    public boolean verifiedtick;


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public boolean isEnable() {
        return enabled;
    }


    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }


    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }


    public Authorities getAuthorities() {
        return authorities;
    }


}
