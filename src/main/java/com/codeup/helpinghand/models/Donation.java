package com.codeup.helpinghand.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long donation_id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column()
    private Date date;

    @Column(length = 255)
    private String picture;

    @Column()
    private Boolean is_approved;

    @Column()
    private Boolean is_fulfilled;

    @Column()
    @JoinColumn (name = "user_id")
    private long claimant_id;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn (name = "category_id")
    private Category category;

    @OneToOne
    @JoinColumn (name = "request_id")
    private Request request;

    public Donation() {

    }

    public Donation(long donation_id, String title, String description, Date date, String picture, Boolean is_approved, Boolean is_fulfilled, long claimant_id, User user, Category category, Request request) {
        this.donation_id = donation_id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.picture = picture;
        this.is_approved = is_approved;
        this.is_fulfilled = is_fulfilled;
        this.claimant_id = claimant_id;
        this.user = user;
        this.category = category;
        this.request = request;
    }

    public long getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(long donation_id) {
        this.donation_id = donation_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getIs_approved() {
        return is_approved;
    }

    public void setIs_approved(Boolean is_approved) {
        this.is_approved = is_approved;
    }

    public Boolean getIs_fulfilled() {
        return is_fulfilled;
    }

    public void setIs_fulfilled(Boolean is_fulfilled) {
        this.is_fulfilled = is_fulfilled;
    }

    public long getClaimant_id() {
        return claimant_id;
    }

    public void setClaimant_id(long claimant_id) {
        this.claimant_id = claimant_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
