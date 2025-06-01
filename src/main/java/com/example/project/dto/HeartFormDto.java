package com.example.project.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class HeartFormDto {

        private Long heartSequence;
        private Long userSequence;
        private String heartContent;
}
