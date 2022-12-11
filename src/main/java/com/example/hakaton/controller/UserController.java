package com.example.hakaton.controller;

import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.service.StoryService;
import com.example.hakaton.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * @apiDefine USERS
     * ПОЛЬЗОВАТЕЛИ
     */

    private final UserService userService;
    private final StoryService storyService;

    public UserController(UserService userService, StoryService storyService) {
        this.userService = userService;
        this.storyService = storyService;
    }


    @PostMapping
    public ResponseEntity addUser(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/story/{id}")
    public ResponseEntity getUserStory(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(storyService.getUserStory(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity findUsers(@RequestParam String filter) {
        try {
            return ResponseEntity.ok(userService.findByString(filter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
