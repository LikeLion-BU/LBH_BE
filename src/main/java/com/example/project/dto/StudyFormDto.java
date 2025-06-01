package com.example.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class StudyFormDto {

    private Long userSequence;
    private String title;
    private String content;

}
