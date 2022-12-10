package com.example.hakaton.controller;

import com.example.hakaton.service.BonusesService;
import com.example.hakaton.service.StoryService;
import com.example.hakaton.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trash")
public class TrashCanController {

    private final StoryService storyService;

    public TrashCanController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/story/{id}")
    public ResponseEntity getTrashCanStory(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(storyService.getTrashCanStory(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
