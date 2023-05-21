package com.example.demo.controller;


import java.util.*;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

//spring framework가 컨트롤러로 인식을 함.
@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    //id인자를 받아서 해당 유저를 json형태로 반환하는 api
    //데이터를 조회하는 api는 @GetMapping 어노테이션을 사용한다.
    @GetMapping("/user/{id}")
    public Optional<UserProfile> selectUserProfile(@PathVariable("id") int id) {
    //@PathVariable("id")는 path에서 {id}부분을 인식해서 String id로 넘긴다.
        return userProfileService.selectUserProfile(id);
    }

    //핸드폰번호로 user 찾기.
    @GetMapping("/user/login/{phone}")
    public Optional<UserProfile> selectPhoneUserProfile(@PathVariable("phone") String phone) {
        return userProfileService.selectPhoneUserProfile(phone);
    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserList() { //유저 정보 전체를 반환
        return userProfileService.getUserList();
    }

    @PostMapping("/user/register")
    public UserProfile insertUserProfile(@RequestBody UserProfile userprofile) {
        return userProfileService.insertUserProfile(userprofile);
    }


//
//    //데이터를 생성할 떄는 PutMapping사용
//    @PutMapping("/user/{id}")
//    public void insertUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
//
//    }
//
//    //데이터 수정 @PostMapping사용!
//    @PostMapping("/user/{id}")
//    public void updateUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
//
//    }
//
//    //데이터 삭제 @DeleteMapping
//    @DeleteMapping("/user/{id}")
//    public void deleteUserProfile(@PathVariable("id") String id) {
//
//    }


}
