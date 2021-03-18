package com.codeup.helpinghand.repositories;

import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.Request;
import com.codeup.helpinghand.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Same thing below for requests
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query(value = "SELECT * FROM donations WHERE is_approved = true ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Donation>lastFive();

    @Query(value = "SELECT * FROM donations WHERE is_approved = false ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Donation>lastFivePending();

    @Query(value = "SELECT * from donations WHERE is_approved = true ORDER BY date", nativeQuery = true)
    List<Donation> allDonations();

    @Query(value = "SELECT * from donations WHERE is_approved = false ORDER BY date", nativeQuery = true)
    List<Donation> allPendingDonations();

    @Query(value = "SELECT * from donations WHERE is_approved = true ORDER BY donator_id and date DESC LIMIT 5", nativeQuery = true)
    List<Donation> lastFiveUserDonations();

    List<Donation> findAllByUserAndIsApproved(User user);

    List<Donation> findAllByUser(User user);
}
