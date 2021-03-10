package com.codeup.helpinghand.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long request_id;

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

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn (name = "category_id")
    private Category category;

    public Request() {

    }

    public Request(long request_id, String title, String description, Date date, String picture, Boolean is_approved, Boolean is_fulfilled, User user, Category category) {
        this.request_id = request_id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.picture = picture;
        this.is_approved = is_approved;
        this.is_fulfilled = is_fulfilled;
        this.user = user;
        this.category = category;
    }

    public long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(long request_id) {
        this.request_id = request_id;
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
