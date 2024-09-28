package com.dailycodework.dream_shops.service.cart;
import com.dailycodework.dream_shops.Model.Cart;
import java.math.BigDecimal;
public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
}
