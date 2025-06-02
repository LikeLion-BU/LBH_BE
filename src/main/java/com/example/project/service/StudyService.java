package com.example.project.service;


import com.example.project.dto.StudyFormDto;
import com.example.project.entity.Study;
import com.example.project.entity.User;

import java.util.List;

public interface StudyService {

    void Studysave(StudyFormDto studyFormDto);

    void saveReview(String title, String content, User user);

    User findByUser(String userId);

    List<Study> findAll();

    List<Study> getStudiesByUser(User user); // 이거 추가
}


