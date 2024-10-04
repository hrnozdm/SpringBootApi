package com.dailycodework.dream_shops.repository;

import com.dailycodework.dream_shops.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {


    void deleteAllById(Long id);
}
