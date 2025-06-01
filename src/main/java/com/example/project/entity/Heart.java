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
@Table(name = "Heart")


public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HEART_SEQUENCE", length = 36)
    private Long heartSequence;

    @ManyToOne
    @JoinColumn(name = "USER_SEQUENCE") // FK 컬럼
    private User user;
    private String heartContent;
}

