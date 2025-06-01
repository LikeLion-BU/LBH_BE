package com.example.project.service;

import com.example.project.entity.Heart;
import com.example.project.entity.User;

import java.util.List;

public interface HeartService {
    void saveHeart(Heart heart);

    List<Heart> findAll();

}
