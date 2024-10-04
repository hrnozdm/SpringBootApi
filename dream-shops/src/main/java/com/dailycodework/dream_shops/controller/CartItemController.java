package com.dailycodework.dream_shops.controller;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.response.ApiResponse;
import com.dailycodework.dream_shops.service.cart.CartItemService;
import com.dailycodework.dream_shops.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

      private final CartItemService cartItemService;
      private final CartService cartService;

      @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemCart(@RequestParam(required = false) Long cartId, @RequestParam Long productId, @RequestParam Integer quantity){
        try {
            if (cartId==null){
                cartId=cartService.initializeNewCart();
            }
            cartItemService.addItemToCart(cartId,productId,quantity);
            return ResponseEntity.ok(new ApiResponse("Add Item Success",null));
        } catch (IllegalArgumentException | ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("An unexpected error occurred: " + e.getMessage(), null));
        }
    }



      @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
      public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId){
          try {
              cartItemService.removeItemFromCart(cartId,itemId);
              return ResponseEntity.ok(new ApiResponse("Remove Item Success",null));
          }catch (ResourceNotFoundException e){
              return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
          }

      }

      @PutMapping("/cart/{cartId}/item/{itemId}/update")
      public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,@PathVariable Long itemId,@RequestParam Integer quantity){

          try {
              cartItemService.updateItemQuantity(cartId,itemId,quantity);
              return ResponseEntity.ok(new ApiResponse("Update Item Success",null));
          }catch (ResourceNotFoundException e){
              return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
          }

      }










}
