package com.dailycodework.dream_shops.service.cart;
import com.dailycodework.dream_shops.Model.Cart;
import com.dailycodework.dream_shops.Model.CartItem;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.repository.CartItemRepository;
import com.dailycodework.dream_shops.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Cart Not Found"));
        BigDecimal totalAmount=cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
      Cart cart=getCart(id);
      cartRepository.deleteAllByCartId(id);
      cart.getItems().clear();
      cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart=getCart(id);
        return cart.getTotalAmount();
    }
}
