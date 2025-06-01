package com.example.project.apiController;

import com.example.project.entity.Heart;
import com.example.project.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
@Slf4j
public class ApiHeartController {

    private final HeartService heartService;

    //  1. 하트 등록 API
    @PostMapping("/heartWrite")
    public ResponseEntity<?> writeHeart(@RequestBody Map<String, String> requestData) {
        String content = requestData.get("content");
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("내용이 비어 있습니다.");
        }

        Heart heart = new Heart();
        heart.setHeartContent(content);
        heartService.saveHeart(heart);

        return ResponseEntity.ok("하트 저장 성공");
    }

    // 2. 하트 전체 목록 조회 API
    @GetMapping("/heartList")
    public ResponseEntity<List<Heart>> getHeartList() {
        List<Heart> heartList = heartService.findAll();
        return ResponseEntity.ok(heartList);
    }
}