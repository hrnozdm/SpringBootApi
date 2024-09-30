package com.dailycodework.dream_shops.controller;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.response.ApiResponse;
import com.dailycodework.dream_shops.service.cart.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

      private final CartItemService cartItemService;

      @PostMapping("/item/add")
      public ResponseEntity<ApiResponse> addItemCart(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam Integer quantity){
          try {
              cartItemService.addItemToCart(cartId,productId,quantity);
              return ResponseEntity.ok(new ApiResponse("Add Item Success",null));
          }catch (ResourceNotFoundException e){
              return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
          }
      }





}
