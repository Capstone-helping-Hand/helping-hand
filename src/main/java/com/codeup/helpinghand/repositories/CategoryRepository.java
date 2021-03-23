package com.codeup.helpinghand.repositories;

import com.codeup.helpinghand.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByType (String type);
}
