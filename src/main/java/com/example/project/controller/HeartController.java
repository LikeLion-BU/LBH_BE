package com.example.project.controller;

import com.example.project.entity.Heart;
import com.example.project.entity.Study;
import com.example.project.entity.User;
import com.example.project.service.HeartService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HeartController {

    private final UserService userService;
    private final HeartService heartService;

    @GetMapping("/heart")
    public String showHeartPage(Model model) {
        // 전체 하트(Heart) 리스트를 가져와서 모델에 추가
        List<Heart> allHeart = heartService.findAll();
        model.addAttribute("heartList", allHeart);
        return "study/heart";
    }

    @PostMapping("/heart")
    public String postHeart(@RequestParam("content") String content) {
        Heart heart = new Heart();
        heart.setHeartContent(content);
        heartService.saveHeart(heart);
        return "redirect:/heart";  // 여기서는 ResponseBody 필요 없음
    }
}
