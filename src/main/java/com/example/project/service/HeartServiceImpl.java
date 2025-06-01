package com.example.project.service;

import com.example.project.entity.Heart;
import com.example.project.entity.Study;
import com.example.project.entity.User;
import com.example.project.repository.HeartRepository;
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
public class HeartServiceImpl implements HeartService {

    @Autowired
    private final HeartRepository heartRepository;


    @Override
    public void saveHeart(Heart heart) {
        heartRepository.save(heart);
    }

    @Override
    public List<Heart> findAll() {
        return heartRepository.findAll();
    }
}
