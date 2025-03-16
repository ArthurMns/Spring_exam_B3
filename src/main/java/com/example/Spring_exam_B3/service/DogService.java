package com.example.Spring_exam_B3.service;

import com.example.Spring_exam_B3.entity.Dog;
import com.example.Spring_exam_B3.repo.DogRepository;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Iterable<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public void createDog(Dog dog) {
        dogRepository.save(dog);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }

    public boolean updateDog(Long id, Dog updatedDog) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()) {
            Dog existingDog = optionalDog.get();
            existingDog.setBreed(updatedDog.getBreed());
            existingDog.setImg_url(updatedDog.getImg_url());
            dogRepository.save(existingDog);
            return true;
        }
        return false;
    }

    public boolean existsByBreed(String breed) {
        return dogRepository.existsByBreed(breed);
    }
}
