package com.example.hakaton.controller;

import com.example.hakaton.entity.TrashCanEntity;
import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.service.StoryService;
import com.example.hakaton.service.TrashCanService;
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

    @PutMapping("/{id}")
    public ResponseEntity setDisabled(@PathVariable Long id,
                                      @RequestParam boolean disabled) {
        try {
            trashCanService.setDisabled(id, disabled);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTrashCan(@PathVariable Long id) {
        try {
            trashCanService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
