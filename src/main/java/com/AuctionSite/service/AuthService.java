package com.AuctionSite.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.AuctionSite.model.User;
import com.AuctionSite.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입 처리
    public void registerUser(User user) {
        userRepository.save(user);  // 비밀번호를 암호화하지 않고 그대로 저장
    }

    // 사용자 인증
    public User authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        
        // 사용자가 존재하고 비밀번호가 일치하면 User 객체를 반환
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        }
        return null;  // 인증 실패 시 null 반환
    }
}
