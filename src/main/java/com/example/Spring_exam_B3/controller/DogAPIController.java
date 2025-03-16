package com.example.Spring_exam_B3.controller;

import com.example.Spring_exam_B3.dto.DogDTO;
import com.example.Spring_exam_B3.entity.Dog;
import com.example.Spring_exam_B3.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dogs")
public class DogAPIController {

    @Autowired
    DogService dogService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/test")
    public String test() {
        return "This is ok, you can get in";
    }

    @PreAuthorize("hasRole('API_CALL')")
    @GetMapping("/apicall/getAllBreeds")
    public Map<String, Object> getAllBreeds() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity("https://dog.ceo/api/breeds/list/all", Map.class);

        return response.getBody();
    }

    @PreAuthorize("hasRole('API_CALL')")
    @GetMapping("/apicall/getRandomBreed")
    public String getRandomBreed() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity("https://dog.ceo/api/breed/mix/images/random", Map.class);

        if (response.getBody() != null && response.getBody().containsKey("message")) {
            return (String) response.getBody().get("message");
        }

        return "Erreur : impossible de récupérer l'image";
    }

    @PreAuthorize("hasRole('SCRAPER')")
    @GetMapping("/scrapDog")
    public String scrapDog() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity("https://dog.ceo/api/breeds/list/all", Map.class);

        if (response.getBody() == null || !response.getBody().containsKey("message")) {
            return "Erreur lors de la récupération des races";
        }

        Map<String, List<String>> breeds = (Map<String, List<String>>) response.getBody().get("message");

        breeds.forEach((key, value) -> {
            if (value.size() > 0) {
                value.forEach(subBreed -> {
                    String breedName = key + " " + subBreed;
                    if (!dogService.existsByBreed(breedName)) {
                        Dog dog = new Dog();
                        dog.setBreed(breedName);
                        dogService.createDog(dog);
                    }
                });
            } else {
                if (!dogService.existsByBreed(key)) {
                    Dog dog = new Dog();
                    dog.setBreed(key);
                    dogService.createDog(dog);
                }
            }
        });

        return breeds.toString();
    }

    @PreAuthorize("hasRole('CRUD')")
    @PostMapping("/crud/createDog")
    public String createDog(@RequestBody DogDTO dogDTO) {

        String breed = dogDTO.getBreed();
        String img_url = dogDTO.getImg_url();

        Dog dog = new Dog();
        dog.setBreed(breed);
        dog.setImg_url(img_url);

        dogService.createDog(dog);
        return "Dog created successfully";
    }

    @PreAuthorize("hasRole('CRUD')")
    @GetMapping("/crud/getDogById/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogService.getDogById(id);
    }

    @PreAuthorize("hasRole('CRUD')")
    @GetMapping("/crud/getAllDogs")
    public Iterable<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @PreAuthorize("hasRole('CRUD')")
    @PutMapping("/crud/updateDog/{id}")
    public String updateDog(@PathVariable Long id, @RequestBody DogDTO dogDTO) {

        String breed = dogDTO.getBreed();
        String img_url = dogDTO.getImg_url();

        Dog dog = new Dog();
        dog.setBreed(breed);
        dog.setImg_url(img_url);

        dogService.updateDog(id, dog);
        return "Dog updated successfully";
    }

    @PreAuthorize("hasRole('CRUD')")
    @DeleteMapping("/crud/deleteDog/{id}")
    public String deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
        return "Dog deleted successfully";
    }

}
