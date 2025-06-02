package com.example.project.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class JoinFormDto {
    private Long userSequence;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private String phone;

}


