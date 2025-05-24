package com.example.project.dto;

import com.example.project.entity.User;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class JoinFormDto {

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String phone;

    /**
     * DTO → Entity 변환 메서드
     */
    public User toEntity() {
        return User.builder()
                .id(this.id)
                .password(this.password)
                .nickname(this.nickname)
                .email(this.email)
                .phone(this.phone)
                .build();
    }
}

