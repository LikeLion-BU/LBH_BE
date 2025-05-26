package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_SEQUENCE", length = 36)
    private Long userSequence;

    private String userId;
    private String nickname;
    private String password;
    private String email;
    private String phone;

}

