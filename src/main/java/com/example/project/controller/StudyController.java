package com.example.project.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class StudyController {
    @GetMapping("/studyRoom")
    public String showStudyPage(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        return "study/studyRoom"; // templates/study/studyRoom.html
    }

    @GetMapping("/write")
    public String showWriteForm(HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        return "study/write";
    }
}


