package com.AuctionSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AuctionSite.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
