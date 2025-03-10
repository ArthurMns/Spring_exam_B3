package com.example.Spring_exam_B3.controller;

import com.example.Spring_exam_B3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String getUsers() {
        return userService.getUser("Jean Neymar").toString();
    }

}
