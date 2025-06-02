package com.example.project.repository;

import com.example.project.entity.Heart;
import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long>{

    List<Heart> findByUser(User user);
}
