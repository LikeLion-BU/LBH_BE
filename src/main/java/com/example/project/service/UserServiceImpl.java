package com.example.project.service;

import com.example.project.dto.JoinFormDto;
import com.example.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.entity.User;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * RODO - 회원 등록 또는 업데이트 이 메서드는 JoinFormDto에 제공된 정보를 기반으로 새 회원을 등록하거나 기존 회원을
     * 업데이트합니다. 이 메서드는 JoinFormDto의 데이터를 사용하여 Member 객체를 생성하고 MemberRepository를 사용하여
     * 저장합니다.
     */
    @Override
    @Transactional
    public String entitySave(JoinFormDto joinFormDto) {
        User user = User.builder()
                .userId(joinFormDto.getUserId())
                .nickname(joinFormDto.getNickname())
                .password(joinFormDto.getPassword())
                .email(joinFormDto.getEmail())
                .phone(joinFormDto.getPhone())
                .build();

        return userRepository.save(user).getUserSequence().toString();
    }

    /*
     * TODO - 회원 저장 이 메서드는 MemberRepository를 사용하여 Member 객체를 데이터베이스에 저장합니다. 이
     * 메서드에는 @Transactional 주석이 추가되어 작업이 원자적임을 보장합니다.
     */
    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> login(String userId, String password) {
        return userRepository.findByUserId(userId)
                .filter(user -> user.getPassword().equals(password));
    }

    // ✅ 추가: userId로 사용자 조회
    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);

    }
}

