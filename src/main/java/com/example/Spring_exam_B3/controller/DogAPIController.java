package com.example.Spring_exam_B3.controller;

import com.example.Spring_exam_B3.dto.DogDTO;
import com.example.Spring_exam_B3.entity.Dog;
import com.example.Spring_exam_B3.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/test")
    public String test() {
        return "This is ok, you can get in";
    }

    @GetMapping("/getAllBreeds")
    public Map<String, Object> getAllBreeds() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity("https://dog.ceo/api/breeds/list/all", Map.class);

        return response.getBody();
    }

    @GetMapping("/getRandomBreed")
    public String getRandomBreed() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity("https://dog.ceo/api/breed/mix/images/random", Map.class);

        if (response.getBody() != null && response.getBody().containsKey("message")) {
            return (String) response.getBody().get("message");
        }

        return "Erreur : impossible de récupérer l'image";
    }

    @GetMapping("/scrapDog")
    public String scrapDog() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity("https://dog.ceo/api/breeds/list/all", Map.class);

        if (response.getBody() == null || !response.getBody().containsKey("message")) {
            return "Erreur lors de la récupération des races";
        }

        // Récupération des données sous forme de Map<String, List<String>>
        Map<String, List<String>> breeds = (Map<String, List<String>>) response.getBody().get("message");

        // Affichage des races dans la console
        breeds.forEach((key, value) -> {
            if (value.size() > 0) {
                value.forEach(subBreed -> {
                    String breedName = key + " " + subBreed;
                    if (!dogService.existsByBreed(breedName)) { // Vérifie si la race existe
                        Dog dog = new Dog();
                        dog.setBreed(breedName);
                        dogService.createDog(dog);
                    }
                });
            } else {
                if (!dogService.existsByBreed(key)) { // Vérifie si la race existe
                    Dog dog = new Dog();
                    dog.setBreed(key);
                    dogService.createDog(dog);
                }
            }
        });

        return breeds.toString();
    }

    @PostMapping("/createDog")
    public String createDog(@RequestBody DogDTO dogDTO) {

        String breed = dogDTO.getBreed();
        String img_url = dogDTO.getImg_url();

        Dog dog = new Dog();
        dog.setBreed(breed);
        dog.setImg_url(img_url);

        dogService.createDog(dog);
        return "Dog created successfully";
    }

    @GetMapping("/getDogById")
    public Dog getDogById(Long id) {
        return dogService.getDogById(id);
    }

    @PostMapping("/updateDog")
    public String updateDog(@RequestBody DogDTO dogDTO) {
        String breed = dogDTO.getBreed();
        String img_url = dogDTO.getImg_url();

        Dog dog = new Dog();
        dog.setBreed(breed);
        dog.setImg_url(img_url);

        dogService.updateDog(dog);
        return "Dog updated successfully";
    }

    @PostMapping("/deleteDog")
    public String deleteDog(Long id) {
        dogService.deleteDog(id);
        return "Dog deleted successfully";
    }



}
