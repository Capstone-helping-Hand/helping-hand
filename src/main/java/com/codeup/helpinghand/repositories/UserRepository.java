package com.codeup.helpinghand.repositories;

import com.codeup.helpinghand.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
