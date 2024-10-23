package com.AuctionSite.service;

import org.springframework.stereotype.Service;

import com.AuctionSite.model.User;
import com.AuctionSite.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String password, String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
    

        userRepository.save(user);  // 데이터베이스에 사용자 저장
    }
}
