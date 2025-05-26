package com.example.project.dto;

import lombok.Data;

public class StudyFormDto {
    @Data
    public class StudyDto {
        private Long userSequence;
        private String title;
        private String content;
    }
}
