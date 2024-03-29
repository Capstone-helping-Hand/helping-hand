package com.codeup.helpinghand.repositories;

import com.codeup.helpinghand.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//Same thing below for requests
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query(value = "SELECT * FROM donations WHERE is_approved = 1 ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Donation>lastFive();

    @Query(value = "SELECT * FROM donations WHERE is_approved = 0 ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Donation>lastFivePending();

    @Query(value = "SELECT * from donations WHERE is_approved = 1 AND claimant_id IS null ORDER BY date", nativeQuery = true)
    List<Donation> allApprovedDonations();

    @Query(value = "SELECT * from donations WHERE is_approved = 1 AND claimant_id IS NOT null ORDER BY date", nativeQuery = true)
    List<Donation> allClaimedDonation();

    @Query(value = "SELECT * from donations WHERE is_approved = 0 ORDER BY date", nativeQuery = true)
    List<Donation> allPendingDonations();

    @Query(value = "SELECT * FROM donations WHERE is_approved and donator_id=? ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Donation>lastFiveForUser(long donator_id);

    @Query(value = "SELECT * FROM donations as donation JOIN users as user ON user_id WHERE claimant_id and user_id = ? ORDER BY date", nativeQuery = true)
    List<Donation>claimDonation(long claimant_id);

}
