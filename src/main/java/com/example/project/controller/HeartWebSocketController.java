package com.example.project.controller;

import com.example.project.entity.Heart;
import com.example.project.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HeartWebSocketController {

    private final HeartService heartService;

    @MessageMapping("/heart")
    @SendTo("/topic/hearts")
    public Heart handleHeart(Heart heart) {
        if (heart.getHeartContent() == null || heart.getHeartContent().trim().isEmpty()) {
            log.warn("빈 heartContent 수신됨 → 무시");
            return null; // 또는 예외 던지기
        }

        heartService.saveHeart(heart); // DB에 저장
        return heart; // 클라이언트들에게 전송
    }

}