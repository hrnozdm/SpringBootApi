package com.dailycodework.dream_shops.repository;
import com.dailycodework.dream_shops.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>{
}
