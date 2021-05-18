package com.example.demo.Model;

import javax.persistence.*;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userdetailid;


    public String firstname;
    public String lastname;
    public long contactno;
    public String userCountry;
    @Column(columnDefinition = "boolean default false")
    public boolean verifiedtick;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "username", nullable = false)
    public Users users;


    public int getUserdetailid() {
        return userdetailid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getContactno() {
        return contactno;
    }

    public void setContactno(long contactno) {
        this.contactno = contactno;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public boolean isVerifiedtick() {
        return verifiedtick;
    }

    public void setVerifiedtick(boolean verifiedtick) {
        this.verifiedtick = verifiedtick;
    }
}
