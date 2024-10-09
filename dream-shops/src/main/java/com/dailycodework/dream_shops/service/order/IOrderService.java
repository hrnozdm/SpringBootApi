package com.dailycodework.dream_shops.service.order;
import com.dailycodework.dream_shops.Model.Order;
import com.dailycodework.dream_shops.dto.OrderDto;
import java.util.List;
public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
