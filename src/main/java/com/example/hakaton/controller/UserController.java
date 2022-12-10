package com.example.hakaton.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity hello() {
        try {
            return ResponseEntity.ok("hello world");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
