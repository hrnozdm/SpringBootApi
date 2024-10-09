package com.dailycodework.dream_shops.controller;
import com.dailycodework.dream_shops.Model.User;
import com.dailycodework.dream_shops.response.ApiResponse;
import com.dailycodework.dream_shops.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;

    public ResponseEntity<ApiResponse> getUserById(Long id){
        User user = userService.getUserById(id);
        return null;
    }



}
