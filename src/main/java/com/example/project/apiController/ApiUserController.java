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
@RestController // JSON 응답용
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
            return ResponseEntity.ok().body((Object) user); // 👈 명시적 Object 처리
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("아이디 또는 비밀번호 오류");
        }
    }

    // JSON 형식으로 회원가입 처리
    @PostMapping("/join")
    public JoinFormDto join(@RequestBody JoinFormDto joinFormDto) {
        log.info("회원가입 요청: {}", joinFormDto);
        userService.entitySave(joinFormDto);
        return joinFormDto; // 응답으로 등록된 사용자 정보 반환
    }
}
