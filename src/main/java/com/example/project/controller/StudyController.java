package com.example.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import com.example.project.entity.Study;
import com.example.project.entity.User;
import com.example.project.service.StudyService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudyController {

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


    @GetMapping("/write")
    public String showWriteForm(HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        return "study/write";
    }

}


