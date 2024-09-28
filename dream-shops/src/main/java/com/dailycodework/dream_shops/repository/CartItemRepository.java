package com.dailycodework.dream_shops.repository;
import com.dailycodework.dream_shops.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{

}
