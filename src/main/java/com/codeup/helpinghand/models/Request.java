package com.codeup.helpinghand.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column()
    private Date date;

    @Column(length = 255)
    private String picture;

    @Column()
    private Boolean isApproved;

    @Column()
    private Boolean isFulfilled;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

    public Request() {

    }

    public Request(long requestId, String title, String description, Date date, String picture, Boolean isApproved, Boolean isFulfilled, User user, Category category) {
        this.requestId = requestId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.picture = picture;
        this.isApproved = isApproved;
        this.isFulfilled = isFulfilled;
        this.user = user;
        this.category = category;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
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

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Boolean getFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        isFulfilled = fulfilled;
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
}
