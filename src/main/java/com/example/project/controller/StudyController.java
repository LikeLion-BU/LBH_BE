package com.example.project.controller;

import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.project.entity.Study;
import com.example.project.entity.User;
import com.example.project.service.StudyService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudyController {

    private final UserService userService;
    private final StudyService studyService;

    @GetMapping("/studyRoom")
    public String showStudyPage(HttpSession session, Model model) {
        // 로그인 여부 확인
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login"; //
        }

        // 로그인 한 유저의 후기 목록 조회
        List<Study> myStudies = studyService.getStudiesByUser(loginUser);
        // 모델에 후기 목록 담기
        model.addAttribute("studies", myStudies);

        return "study/studyRoom"; //
    }


    @PostMapping("/write")
    public ResponseEntity<?> submitReview(@RequestParam String title,
                                          @RequestParam String content) {
        try {
            // 로그인 사용자 없이 익명으로 저장 (user는 null)
            studyService.saveReview(title, content, null);
            return ResponseEntity.ok("리뷰 등록 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 등록 실패");
        }
    }

}


