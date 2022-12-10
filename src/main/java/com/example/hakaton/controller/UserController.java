package com.example.hakaton.controller;

import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.service.BonusesService;
import com.example.hakaton.service.StoryService;
import com.example.hakaton.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    /**
     * @api {post} /users Добавление пользователя
     * @apiName addUser
     * @apiGroup USERS
     * @apiBody {String} email Корпоративная почта пользователя
     * @apiBody {String} username Username пользователя
     * @apiBody {String} [about] информация о пользователе
     * @apiBody {String} [post] должность пользователя
     * @apiBody {String} [project] проект пользователя
     * @apiBody {boolean} [admin=false] является ли пользователь админом
     * @apiBody {Date} [birthDate] дата рождения пользователя
     * @apiHeader {String} accessToken Аксес токен
     * @apiError (Error 401) AccessTokenIsNotValidException Не валидный AccessToken
     * @apiError (Error 400) UserAlreadyExistException Пользователь уже существует
     **/

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @api {get} /users/[id] Получение пользователя по айди
     * @apiName getOneUser
     * @apiGroup USERS
     * @apiParam {Number} id Уникальный идентефикатор пользователя
     * @apiHeader {String} accessToken Аксес токен
     * @apiSuccess {Long} id id пользователя
     * @apiSuccess {String} email email пользователя
     * @apiSuccess {String} username имя пользователя
     * @apiSuccess {String} about информация о пользователе
     * @apiSuccess {String} post должность пользователя
     * @apiSuccess {String} project проект пользователя
     * @apiSuccess {boolean} admin является ли пользователь админом
     * @apiSuccess {Date} birthDate дата рождения пользователя
     * @apiError (Error 401) AccessTokenIsNotValidException Не валидный AccessToken
     **/

    @GetMapping("/{id}")
    public ResponseEntity getOneUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @api {get} /users?pageNumber=[pageNumber]&size=[size]&sortBy=[sortBy] Получение списка пользователей
     * @apiName getUsers
     * @apiGroup USERS
     * @apiHeader {String} accessToken Аксес токен
     * @apiParam {Number} pageNumber Номер страницы
     * @apiParam {Number} size Количество элементов на странице
     * @apiParam {String} sortBy Фильтр сортировки
     * @apiSuccess {List[User]} users Список всех пользователей (поля id, username, post)
     * @apiError (Error 401) AccessTokenIsNotValidException Не валидный AccessToken
     **/

    @GetMapping()
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @api {delete} /users/[id] Удаление пользователя по айди
     * @apiName deleteUser
     * @apiGroup USERS
     * @apiParam {Number} id Уникальный идентефикатор пользователя
     * @apiHeader {String} accessToken Аксес токен
     * @apiError (Error 401) AccessTokenIsNotValidException Не валидный AccessToken
     * @apiError (Error 400) UserNotFoundException Пользователь с таким id не существует
     **/

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
