package com.AuctionSite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AuctionSite.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // 메서드 정의
}
