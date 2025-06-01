package com.example.project.service;

import com.example.project.dto.StudyFormDto;
import com.example.project.entity.Study;
import com.example.project.entity.User;
import com.example.project.repository.StudyRepository;
import com.example.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudyServiceImpl implements StudyService {

    @Autowired
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void Studysave(StudyFormDto studyFormDto) {
        User user = userRepository.findById(studyFormDto.getUserSequence())
                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));

        Study study = Study.builder()
                .user(user)
                .title(studyFormDto.getTitle())
                .content(studyFormDto.getContent())
                .build();

        studyRepository.save(study);
    }

    @Override
    public List<Study> getStudiesByUser(User user) {
        return studyRepository.findByUser(user); // 유저별 Study 조회
    }

    public void saveReview(String title, String content, User user) {
        Study study = new Study();
        study.setTitle(title);
        study.setContent(content);
        study.setUser(user);
        log.info(String.valueOf(study));
        studyRepository.save(study);
    }
    @Override
    public User findByUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다."));
    }

    @Override
    public List<Study> findAll() {
        return studyRepository.findAll();
    }
}

