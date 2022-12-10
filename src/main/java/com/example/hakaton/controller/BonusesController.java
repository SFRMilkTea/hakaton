package com.example.hakaton.controller;

import com.example.hakaton.service.BonusesService;
import com.example.hakaton.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bonuses")
public class BonusesController {

    private final BonusesService bonusesService;

    public BonusesController(UserService userService, BonusesService bonusesService) {
        this.bonusesService = bonusesService;
    }

    @GetMapping("/add/{id}")
    public ResponseEntity addBonuses(@PathVariable Long id,
                                     @RequestParam int bonuses) {
        try {
            bonusesService.addBonuses(bonuses, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/use/{id}")
    public ResponseEntity useBonuses(@PathVariable Long id,
                                     @RequestParam int bonuses) {
        try {
            bonusesService.useBonuses(bonuses, id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
