package com.codeup.helpinghand.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

//    /// Ending of the finding the last five donations and requests////
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    @JsonIgnore
//    private List<Donation> donationList;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size=5)
//    List<Donation> lastFiveDonations;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size=5)
//    List<Request> lastFiveRequests;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    @JsonIgnore
//    private List<Request> requestList;

    /// Ending of the finding the last five donations and requests////

    public User() {

    }

    public User(long userId, String username, String email, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(User copy) {
        userId = copy.userId;
        username = copy.username;
        email = copy.email;
        password = copy.password;
        role = copy.role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

//    public List<Donation> getDonationList() {
//        return donationList;
//    }

//    public void setDonationList(List<Donation> donationList) {
//        this.donationList = donationList;
//    }
//
//    public List<Request> getRequestList() {
//        return requestList;
//    }
//
//    public void setRequestList(List<Request> requestList) {
//        this.requestList = requestList;
//    }
//    This comment is to create a change
}