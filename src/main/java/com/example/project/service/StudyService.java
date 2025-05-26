package com.example.project.service;


import com.example.project.dto.StudyFormDto;
import com.example.project.entity.Study;
import com.example.project.entity.User;

import java.util.List;

public interface StudyService {
    void Studysave(StudyFormDto studyFormDto);

    // 유저별 작성한 Study 목록 조회
    List<Study> getStudiesByUser(User user);
}
