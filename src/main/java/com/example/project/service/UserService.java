package com.example.project.service;

import com.example.project.dto.JoinFormDto;
import com.example.project.entity.User;

import java.util.Optional;

public interface UserService {

    //회원정보 저장
    String entitySave(JoinFormDto joinFormDto);
    void save(User user);

    //로그인 시 아이디,비번 확인
    Optional<User> login(String userId, String password);
}
