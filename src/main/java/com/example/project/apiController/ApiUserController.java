package com.example.project.apiController;

import com.example.project.dto.JoinFormDto;
import com.example.project.entity.User;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> loginData,
                                        HttpSession session) {
        String userId = loginData.get("userId");
        String password = loginData.get("password");

        Optional<User> optionalUser = userService.login(userId, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            session.setAttribute("loginUser", user);
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("아이디 또는 비밀번호 오류");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 필요");
        }
        return ResponseEntity.ok().body(loginUser);
    }

    @PostMapping("/join")
    public JoinFormDto join(@RequestBody JoinFormDto joinFormDto) {
        userService.entitySave(joinFormDto);
        return joinFormDto;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok().body("로그아웃 성공");
    }
}