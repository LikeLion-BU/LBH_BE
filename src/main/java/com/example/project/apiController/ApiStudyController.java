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

    @GetMapping("/reviews")
    public ResponseEntity<List<Study>> getAllReviews() {
        List<Study> allReviews = studyService.getAllStudies();
        return ResponseEntity.ok(allReviews);
    }

    @PostMapping("/reviews")
    public ResponseEntity<?> writeReviews(@RequestBody Map<String, String> requestData) {
        String title = requestData.get("title");
        String content = requestData.get("content");

        studyService.saveReview(title, content, null);
        return ResponseEntity.ok(Map.of("message", "후기 등록 성공"));
    }
}
