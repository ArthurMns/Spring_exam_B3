package com.example.Spring_exam_B3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/dogs")
public class DogAPIController {

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

}
