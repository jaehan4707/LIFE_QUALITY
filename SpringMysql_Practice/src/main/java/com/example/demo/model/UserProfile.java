package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {
    @Id
    private int id;
    private String name;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Password")
    private String password;
    private String birth;
    private String sex;

    @Builder
    public UserProfile(String name, String phone, String password, String birth, String sex) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.birth = birth;
        this.sex = sex;
    }
}

