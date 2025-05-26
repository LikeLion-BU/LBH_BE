//package com.example.project.service;
//
//import com.example.project.dto.StudyFormDto;
//import com.example.project.entity.Study;
//import com.example.project.entity.User;
//import com.example.project.repository.StudyRepository;
//import com.example.project.repository.UserRepository;
//import lombok.*;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class StudyServiceImpl implements StudyService {
//
//    private final StudyRepository studyRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public void writeStudy(StudyFormDto studyFormDto) {
//        User user = userRepository.findById(studyFormDto.getUserSequence())
//                .orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
//
//        Study study = Study.builder()
//                .user(user)
//                .title(studyFormDto.getTitle())
//                .content(studyFormDto.getContent())
//                .build();
//
//        studyRepository.save(study);
//    }
//}
//
