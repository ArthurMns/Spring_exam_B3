package com.example.Spring_exam_B3.service;

import com.example.Spring_exam_B3.entity.Dog;
import com.example.Spring_exam_B3.repo.DogRepository;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    private final DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Dog getDogById(Long id) {
        Dog dog = dogRepository.findById(id).orElse(null);
        return dog;
    }

    public void createDog(Dog dog) {
        dogRepository.save(dog);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }

    public void updateDog(Dog dog) {
        dogRepository.save(dog);
    }

    public boolean existsByBreed(String breed) {
        return dogRepository.existsByBreed(breed);
    }
}
