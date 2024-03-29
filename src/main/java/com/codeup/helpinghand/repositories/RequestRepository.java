package com.codeup.helpinghand.repositories;

import com.codeup.helpinghand.models.Donation;
import com.codeup.helpinghand.models.Request;
import com.codeup.helpinghand.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
        @Query(value = "SELECT * FROM requests WHERE is_approved = true ORDER BY date DESC LIMIT 5", nativeQuery = true)
        List<Request> lastFive();

        @Query(value = "SELECT * FROM requests WHERE is_approved = false ORDER BY date DESC LIMIT 5", nativeQuery = true)
        List<Request> lastFivePending();

        @Query(value = "SELECT * from requests WHERE is_approved = true ORDER BY date", nativeQuery = true)
        List<Request> allApprovedRequests();

        @Query(value = "SELECT * from requests WHERE is_approved = false ORDER BY date", nativeQuery = true)
        List<Request> allPendingRequests();

        @Query(value = "SELECT * from requests WHERE is_approved = true ORDER BY user_id and date DESC LIMIT 5", nativeQuery = true)
        List<Request> lastFiveUserRequests();

        @Query(value = "SELECT * from requests WHERE is_approved = true ORDER BY user_id and date", nativeQuery = true)
        List<Request> allUserRequests();

        @Query(value = "SELECT * FROM requests WHERE is_fulfilled = true ORDER BY date DESC LIMIT 5", nativeQuery = true)
        List<Request> allFulfilledRequest();

        @Query(value = "SELECT * FROM requests WHERE is_fulfilled = false ORDER BY date DESC LIMIT 5", nativeQuery = true)
        List<Request> fulfilledPending();

        List<Request> findAllByUser(User user);
}
