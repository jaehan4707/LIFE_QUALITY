package com.example.demo.service;

import com.example.demo.model.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public Optional<UserProfile> selectUserProfile(String id) {
        //@PathVariable("id")는 path에서 {id}부분을 인식해서 String id로 넘긴다.
        return userProfileRepository.findById(id);
    }

    public List<UserProfile> getUserList() { //유저 정보 전체를 반환
        return userProfileRepository.findAll();
    }

    public Optional<UserProfile> selectPhoneUserProfile(String phone) {
        return userProfileRepository.findByPhone(phone);
    }

//    public void insertUserProfile() {
//
//    }

    @Transactional
    public void updateUserProfile(String id) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found UserProfile"));
        userProfile.setName("123");
    }

    public void deleteUserProfile() {

    }
}
