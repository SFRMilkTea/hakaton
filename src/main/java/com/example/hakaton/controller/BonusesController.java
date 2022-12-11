package com.example.hakaton.controller;

import com.example.hakaton.service.BonusesService;
import com.example.hakaton.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bonuses")
public class BonusesController {

    /**
     * @apiDefine BONUSES
     * БОНУСЫ
     */

    private final BonusesService bonusesService;

    public BonusesController(UserService userService, BonusesService bonusesService) {
        this.bonusesService = bonusesService;
    }

    /**
     * @api {get} /bonuses/add/[id] Начислить бонусы
     * @apiName addBonuses
     * @apiGroup BONUSES
     * @apiParam {Long} userId Id пользователя
     * @apiParam {int} bonuses Количество начисляемых бонусов
     * @apiParam {Long} trashCanId Id мусорки
     **/

    @GetMapping("/add/{userId}")
    public ResponseEntity addBonuses(@PathVariable Long userId,
                                     @RequestParam int bonuses,
                                     @RequestParam Long trashCanId) {
        try {
            bonusesService.addBonuses(bonuses, userId, trashCanId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/use/{userId}")
    public ResponseEntity useBonuses(@PathVariable Long userId,
                                     @RequestParam int bonuses) {
        try {
            bonusesService.useBonuses(bonuses, userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
