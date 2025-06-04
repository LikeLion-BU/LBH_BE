package com.example.project.controller;

import com.example.project.dto.StudyFormDto;
import com.example.project.entity.Study;
import com.example.project.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudyWebSocketController {

    private final StudyService studyService;

    @MessageMapping("/review")
    @SendTo("/topic/reviews")
    public Study handleReview(StudyFormDto studyFormDto) {
        Study study = new Study();
        study.setTitle(studyFormDto.getTitle());
        study.setContent(studyFormDto.getContent());

        studyService.saveReview(study.getTitle(), study.getContent(), null);
        return study;
    }
}
