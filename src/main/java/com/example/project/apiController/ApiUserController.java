package com.example.project.apiController;

import com.example.project.dto.JoinFormDto;
import com.example.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController // JSON 응답용
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String userId = loginData.get("userId");
        String password = loginData.get("password");

        return userService.login(userId, password)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호 오류"));
    }

    // JSON 형식으로 회원가입 처리
    @PostMapping("/join")
    public JoinFormDto join(@RequestBody JoinFormDto joinFormDto) {
        log.info("회원가입 요청: {}", joinFormDto);
        userService.entitySave(joinFormDto);
        return joinFormDto; // 응답으로 등록된 사용자 정보 반환
    }
}
