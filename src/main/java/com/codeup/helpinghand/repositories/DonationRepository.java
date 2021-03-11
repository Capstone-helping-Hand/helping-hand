package com.codeup.helpinghand.repositories;

import com.codeup.helpinghand.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {

}
