package com.example.project.controller;

import com.example.project.dto.JoinFormDto;
import com.example.project.entity.User;
import com.example.project.repository.UserRepository;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "member/login";
    }

    @PostMapping("/login")
    public String loginAction(@RequestParam String userId,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {
        return userService.login(userId, password)
                .map(user -> {
                    model.addAttribute("user", user);
                    session.setAttribute("loginUser", user);
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
                    return "member/login";
                });
    }

    /* TODO 회원가입 화면 이동 */
    @GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("joinFormDto", new JoinFormDto());
        return "member/join";
    }

    // 폼 제출 처리
    @PostMapping("/join")
    public String joinAction(@ModelAttribute("joinFormDto") JoinFormDto joinFormDto,
                             BindingResult bindingResult,
                             HttpSession session) {

        // 1. 회원 저장
        userService.entitySave(joinFormDto);

        // 2. 방금 가입한 사용자 정보를 가져와 세션에 저장 (자동 로그인 효과)
        User user = userService.findByUserId(joinFormDto.getUserId())
                .orElseThrow(() -> new RuntimeException("가입한 유저 정보를 찾을 수 없습니다."));

        session.setAttribute("loginUser", user); //

        // 3. 인덱스로 이동
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 전체 삭제
        return "redirect:/";
    }

}
