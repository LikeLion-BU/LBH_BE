package com.example.project.controller;

import com.example.project.dto.JoinFormDto;
import com.example.project.entity.User;
import com.example.project.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "member/login";
    }

    @PostMapping("/login")
    public String loginAction(@RequestParam String id, @RequestParam String password, Model model) {
        Optional<User> userOptional = userRepository.findByLoginId(loginId);
        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(password)) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀렸습니다.");
            return "member/login";  // 실패 시 다시 로그인 페이지로
        }

        // 로그인 성공 처리 (예: 세션에 사용자 정보 저장)
        User user = userOptional.get();
        // session.setAttribute("loginUser", user);

        return "redirect:/";  // 메인 페이지로 리다이렉트
    }

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("joinFormDto", new JoinFormDto());
        return "member/join";          // -> templates/join.html
    }

    // 폼 제출 처리
    @PostMapping("/join")
    public String joinAction(
            @Valid @ModelAttribute("joinFormDto") JoinFormDto joinFormDto,
            BindingResult bindingResult,
            Model model
    ) {
        // 검증 에러 발생 시 다시 가입 폼으로
        if (bindingResult.hasErrors()) {
            return "member/join";
        }

        System.out.println(joinFormDto.toString());
        User user = joinFormDto.toEntity();
        User saved = userRepository.save(user);
        return "redirect:/login";
    }

}
