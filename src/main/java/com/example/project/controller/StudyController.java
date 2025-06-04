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
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudyController {

    private final UserService userService;
    private final StudyService studyService;

    @GetMapping("/studyRoom")
    public String showStudyPage(Model model) {
        // 전체 후기 조회 (비회원도 접근 가능)
        List<Study> allStudies = studyService.getAllStudies();
        model.addAttribute("studies", allStudies);
        return "study/studyRoom";
    }

    @PostMapping("/write")
    public ResponseEntity<?> submitReview(@RequestBody Map<String, String> data) {
        try {
            String title = data.get("title");
            String content = data.get("content");

            // 익명 유저 허용
            studyService.saveReview(title, content, null);

            return ResponseEntity.ok("리뷰 등록 성공");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("리뷰 등록 실패: " + e.getMessage());
        }
    }

    @GetMapping("/reviews")
    @ResponseBody
    public List<Study> getAllReviews() {
        return studyService.getAllStudies();
    }

}


