package com.codeup.helpinghand.models;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT(20)")
    private long donationId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column()
    private Date date;

    @Column()
    private String picture;

    @Column()
    private Boolean isApproved;

    @Column()
    private Boolean isFulfilled;

    @ManyToOne
    @JoinColumn (name = "claimant_id") // should be claimant_id ?
    private User claimant;

    @ManyToOne
    @JoinColumn (name = "donator_id") // should be donator_id / donation_id ?
    private User donator;

    @ManyToOne
    @JoinColumn (name = "category_id")
    private Category category;

    public Donation() {

    }

    public Donation(long donationId, String title, String description, Date date, String picture, Boolean isApproved, Boolean isFulfilled, User claimant, User donator, Category category) {
        this.donationId = donationId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.picture = picture;
        this.isApproved = isApproved;
        this.isFulfilled = isFulfilled;
        this.claimant = claimant;
        this.donator = donator;
        this.category = category;
    }

    public long getDonationId() {
        return donationId;
    }

    public void setDonationId(long donationId) {
        this.donationId = donationId;
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

    public User getClaimant() {
        return claimant;
    }

    public void setClaimant(User claimant) {
        this.claimant = claimant;
    }

    public User getDonator() {
        return donator;
    }

    public void setDonator(User donator) {
        this.donator = donator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
