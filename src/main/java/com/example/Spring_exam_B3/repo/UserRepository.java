package com.example.Spring_exam_B3.repo;

import com.example.Spring_exam_B3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByClaimToken(String token);
}
