package com.example.hakaton.controller;

import com.example.hakaton.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class AuthController {

    /**
     * @apiDefine AUTHORIZATION
     * АВТОРИЗАЦИЯ
     */

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * @api {get} /authorization?email=[email@example.ru] Авторизация
     * @apiName authorization
     * @apiGroup AUTHORIZATION
     * @apiParam {String} email Почта пользователя
     * @apiParam {String} password Пароль
     * @apiError (Error 400) UserNotFoundException Пользователь с такой почтой не зарегистрирован
     * @apiError (Error 400) WrongPasswordException Неверный пароль
     **/

    @GetMapping
    public ResponseEntity authorization(@RequestParam String email,
                                        @RequestParam String password) {
        try {
            authService.checkEmail(email, password);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @api {get} /authorization/admin?email=[email@example.ru] Авторизация для администраторов
     * @apiName authorizationForAdmin
     * @apiGroup AUTHORIZATION
     * @apiParam {String} email Корпоративная почта пользователя
     * @apiError (Error 400) UserNotFoundException Пользователь с такой почтой не зарегистрирован
     * @apiError (Error 400) WrongPasswordException Неверный пароль
     **/

    @GetMapping("/admin")
    public ResponseEntity authorizationForAdmin(@RequestParam String email,
                                                @RequestParam String password) {
        try {
            authService.checkAdminEmail(email, password);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
