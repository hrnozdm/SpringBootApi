package com.dailycodework.dream_shops.service.order;
import com.dailycodework.dream_shops.Model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);


}
