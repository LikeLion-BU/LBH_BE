package com.example.project.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import com.example.project.entity.User;

import javax.management.relation.Role;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class JoinFormDto {
    private Long userSequence;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private String phone;

}


