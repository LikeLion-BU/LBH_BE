package com.example.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Study")
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDY_SEQUENCE", length = 36)
    private Long studySequence;

    @ManyToOne
    @JoinColumn(name = "USER_SEQUENCE", nullable = true) // FK 컬럼
    private User user;

    private String title;
    private String content;
}



