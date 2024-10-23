package com.AuctionSite.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 외부 URL과 서버 업로드 경로 중 하나만 사용하도록 개선
    private String imageUrl;   // 외부 URL로 이미지를 참조하는 필드
    private String imagePath;  // 서버에 업로드된 이미지 경로

    private int maxParticipants;

    @ElementCollection
    private List<String> participants = new ArrayList<>();

    // 생성자
    public Item() {}

    public Item(String name, String imageUrl, String imagePath, int maxParticipants) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.imagePath = imagePath;
        this.maxParticipants = maxParticipants;
    }

    // Getter/Setter 메서드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    // 참가자 추가 메서드: 최대 인원을 초과하지 않도록 제한
    public boolean addParticipant(String username) {
        if (participants.size() < maxParticipants) {
            participants.add(username);
            return true;
        }
        return false;
    }

    // 참가자 수가 최대에 도달했는지 확인
    public boolean isFull() {
        return participants.size() >= maxParticipants;
    }
}
