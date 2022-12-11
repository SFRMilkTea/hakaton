package com.example.hakaton.controller;

import com.example.hakaton.entity.TrashCanEntity;
import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.service.BonusesService;
import com.example.hakaton.service.StoryService;
import com.example.hakaton.service.TrashCanService;
import com.example.hakaton.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trash")
public class TrashCanController {

    private final StoryService storyService;
    private final TrashCanService trashCanService;

    public TrashCanController(StoryService storyService, TrashCanService trashCanService) {
        this.storyService = storyService;
        this.trashCanService = trashCanService;
    }

    @GetMapping("/story/{id}")
    public ResponseEntity getTrashCanStory(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(storyService.getTrashCanStory(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity addTrashCan(@RequestBody TrashCanEntity trashCanEntity) {
        try {
            trashCanService.addTrashCan(trashCanEntity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity getTrashCans() {
        try {
            return ResponseEntity.ok(trashCanService.getTrashCans());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public ResponseEntity findTrashCans(@RequestParam String filter) {
        try {
            return ResponseEntity.ok(trashCanService.findByString(filter));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneTrashCan(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(trashCanService.getTrashCan(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
