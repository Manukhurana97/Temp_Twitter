package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
public class Users {

    @Id
    @Email
    public String username;

    public String uniquename;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password;

    @Column(columnDefinition = "boolean default true")
    public boolean enabled;

    @Column(columnDefinition = "boolean default true")
    public boolean accountNonExpired;

    @Column(columnDefinition = "boolean default true")
    public boolean accountNonLocked;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private Authorities authorities;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    @JsonIgnore
    private UserDetails details;

    @Column(columnDefinition = "boolean default false")
    public boolean two_step_verification;






    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enabled;
    }

    public void setEnable(boolean enabled) {
        this.enabled = enabled;
    }


    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }


    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    public boolean isTwo_step_verification() {
        return two_step_verification;
    }

    public void setTwo_step_verification(boolean two_step_verification) {
        this.two_step_verification = two_step_verification;
    }


    public String getUniquename() {
        return uniquename;
    }

    public void setUniquename(String uniquename) {
        this.uniquename = uniquename;
    }

    @Override
    public String toString() {
        return "Users [username=" + username + ", password=" + password + ", enabled=" + enabled + ", authorities="
                + authorities + "]";
    }


}
