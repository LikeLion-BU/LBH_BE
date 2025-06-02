package com.example.project.controller;

import com.example.project.entity.Heart;
import com.example.project.service.HeartService;
import com.example.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HeartController {

    private final UserService userService;
    private final HeartService heartService;

    // 하트 페이지 렌더링
    @GetMapping("/heart")
    public String showHeartPage(Model model) {
        List<Heart> allHeart = heartService.findAll();
        model.addAttribute("heartList", allHeart);
        return "study/heart";
    }

    // 하트 등록 (댓글)
    @PostMapping("/heart")
    public String postHeart(@RequestParam("content") String content) {
        Heart heart = new Heart();
        heart.setHeartContent(content);
        heartService.saveHeart(heart);
        return "redirect:/heart";
    }

    // 좋아요 수 증가 - 최신 하트 대상 (id 필요 없음)
    @PostMapping("/count")
    @ResponseBody
    public String clickHeart() {
        try {
            heartService.incrementLikeCountOfLatest();
            return "success";
        } catch (Exception e) {
            return "오류: " + e.getMessage();
        }
    }
}