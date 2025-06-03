package com.example.project.apiController;

import com.example.project.entity.Study;
import com.example.project.entity.User;
import com.example.project.service.StudyService;
import com.example.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class ApiStudyController {

    private final StudyService studyService;
    private final UserService userService;

//    // 후기 등록 (POST)
//    @PostMapping("/write")
//    public ResponseEntity<?> writeReview(@RequestBody Map<String, String> requestData) {
//        String title = requestData.get("title");
//        String content = requestData.get("content");
//
//        // 로그인 없이 작성 허용
//        studyService.saveReview(title, content, null);  // 작성자 없이 저장
//        return ResponseEntity.ok("후기 등록 성공");
//    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Study>> getAllReviews() {
        List<Study> allReviews = studyService.getAllStudies();
        return ResponseEntity.ok(allReviews);
    }

    // 전체 후기 목록 조회 (GET)
    @PostMapping("/reviews")
    public ResponseEntity<?> writeReviews(@RequestBody Map<String, String> requestData) {
        String title = requestData.get("title");
        String content = requestData.get("content");

        studyService.saveReview(title, content, null);

        // JSON 형식으로 반환
        return ResponseEntity.ok(Map.of("message", "후기 등록 성공"));
    }
}
