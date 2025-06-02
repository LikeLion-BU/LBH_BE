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

    // 1. 하트 등록 API
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

    // 3. 전체 하트 좋아요 수 합계 조회 API
    @GetMapping("/heartCount/total")
    public ResponseEntity<?> getTotalHeartCount() {
        int total = heartService.findAll()
                .stream()
                .mapToInt(Heart::getLikeCount)
                .sum();

        Map<String, Integer> result = new HashMap<>();
        result.put("count", total);
        return ResponseEntity.ok(result);
    }

    // 4. 하트 하나의 likeCount 증가 API
    @PostMapping("/heartCount")
    public ResponseEntity<?> clickHeart() {
        try {
            List<Heart> hearts = heartService.findAll();

            // 없으면 새로 생성
            if (hearts.isEmpty()) {
                Heart heart = new Heart();
                heart.setLikeCount(1);
                heartService.saveHeart(heart);
            } else {
                heartService.incrementLikeCountOfLatest();
            }

            int total = heartService.findAll()
                    .stream()
                    .mapToInt(Heart::getLikeCount)
                    .sum();

            Map<String, Integer> result = new HashMap<>();
            result.put("count", total);
            return ResponseEntity.ok(result);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("오류: " + e.getMessage());
        }
    }

    // 5. 가장 마지막으로 등록된 하트의 ID 조회 API
    @GetMapping("/heartLastId")
    public ResponseEntity<?> getLastHeartId() {
        List<Heart> hearts = heartService.findAll();
        if (hearts.isEmpty()) {
            return ResponseEntity.badRequest().body("등록된 하트가 없습니다.");
        }

        // Heart 엔티티에서 기본키는 heartSequence이므로, 그걸 반환
        Long lastId = hearts.get(hearts.size() - 1).getHeartSequence();

        Map<String, Long> result = new HashMap<>();
        result.put("heartId", lastId);
        return ResponseEntity.ok(result);
    }
}