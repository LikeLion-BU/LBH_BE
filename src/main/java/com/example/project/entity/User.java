package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor    // ← 기본 생성자 자동 추가
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userSequence;
    @Column(nullable = false, unique = true)
    private String id;
    @Column(nullable = false)
    private String password;
    private String nickname;
    private String email;
    private String phone;

    public User(Long userSequence, String id, String password, String nickname, String email, String phone) {
        this.userSequence = userSequence;
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userSequence=" + userSequence +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}





