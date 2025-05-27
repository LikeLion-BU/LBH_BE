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

    // 후기 등록 (POST)
    @PostMapping("/write")
    public ResponseEntity<?> writeReview(@RequestBody Map<String, String> requestData) {
        String userId = requestData.get("userId");
        String title = requestData.get("title");
        String content = requestData.get("content");

        Optional<User> userOptional = userService.findByUserId(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 사용자입니다.");
        }

        studyService.saveReview(title, content, userOptional.get());
        return ResponseEntity.ok("후기 등록 성공");
    }

    // 사용자 ID로 후기 목록 조회 (GET)
    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getUserReviews(@PathVariable String userId) {
        Optional<User> userOptional = userService.findByUserId(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보를 찾을 수 없습니다.");
        }

        List<Study> userReviews = studyService.getStudiesByUser(userOptional.get());
        return ResponseEntity.ok(userReviews);
    }

    // 전체 후기 목록 조회 (GET)
    @PostMapping("/reviews")
    public ResponseEntity<?> writeReview(@RequestBody Map<String, String> requestData,
                                         HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        String title = requestData.get("title");
        String content = requestData.get("content");

        studyService.saveReview(title, content, loginUser);
        return ResponseEntity.ok("후기 등록 성공");
    }
}
