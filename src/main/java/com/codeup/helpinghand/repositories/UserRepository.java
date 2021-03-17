package com.codeup.helpinghand.repositories;

import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

//    private DonationRepository donationDao;
//    @Override
//    public List<Donation> findLastFiveDonations() {
//        return donationDao.createQuery("SELECT * FROM donations WHERE donation_id > (SELECT MAX(donation_id) FROM donations) - 5",
//                Donation.class).getResultList();
//    }
}

