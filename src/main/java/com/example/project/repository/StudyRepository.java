package com.example.project.repository;

import com.example.project.entity.Study;
import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

    // 로그인한 유저가 작성한 Study만 조회
    List<Study> findByUser(User user);

}
