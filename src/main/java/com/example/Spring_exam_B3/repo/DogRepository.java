package com.example.Spring_exam_B3.repo;

import com.example.Spring_exam_B3.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
    boolean existsByBreed(String breed);
}
