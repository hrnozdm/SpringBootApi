package com.dailycodework.dream_shops.repository;

import com.dailycodework.dream_shops.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByName(String name);
}
