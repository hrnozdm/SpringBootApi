package com.dailycodework.dream_shops.service.user;
import com.dailycodework.dream_shops.Model.User;
import com.dailycodework.dream_shops.exception.AlreadyExistsException;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.repository.UserRepository;
import com.dailycodework.dream_shops.request.CreateUserRequest;
import com.dailycodework.dream_shops.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(()->new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return  Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(request.getPassword());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return  userRepository.save(user);
                }) .orElseThrow(() -> new AlreadyExistsException("Oops!" +request.getEmail() +" already exists!"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser->{
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete,()->{
            throw new ResourceNotFoundException("User Not Found");
        });

    }
}
