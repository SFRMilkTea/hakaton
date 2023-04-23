package com.example.hakaton.service;

import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.entity.UserStoryEntity;
import com.example.hakaton.exception.UserAlreadyExistException;
import com.example.hakaton.exception.UserNotFoundException;
import com.example.hakaton.model.User;
import com.example.hakaton.repository.UserRepository;
import com.example.hakaton.repository.UserStoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserStoryRepository userStoryRepository;

    public UserService(UserRepository userRepository, UserStoryRepository userStoryRepository) {
        this.userRepository = userRepository;
        this.userStoryRepository = userStoryRepository;
    }

    public User getUser(Long id) throws UserNotFoundException {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + id + " не существует"));
        return User.toModel(userEntity);
    }

    public List<User> getUsers() {
        Iterable<UserEntity> users = userRepository.findAll();
        ArrayList<User> usersModel = new ArrayList<>();
        for (UserEntity user : users) {
            usersModel.add(User.toModel(user));
        }
        return usersModel;
    }

    public void delete(Long id) throws UserNotFoundException {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException("Пользователь с id: " + id + " не существует");
        }
    }

    public void registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        } else
            throw new UserAlreadyExistException("Пользователь с email " + user.getEmail() + " уже существует");
    }

    public void update(UserEntity user) throws UserNotFoundException {
        if (userRepository.findById(user.getId()).isPresent()) {
            userRepository.save(user);
        } else
            throw new UserNotFoundException("Пользователь с id: " + user.getId() + " не существует");
    }

    public List<User> findByString(String str) {
        Iterable<UserEntity> users = userRepository.findAll();
        ArrayList<User> usersModel = new ArrayList<>();
        for (UserEntity user : users) {
            if (user.getFirstName().toLowerCase().contains(str.toLowerCase())
                    || user.getLastName().toLowerCase().contains(str.toLowerCase())) {
                usersModel.add(User.toModel(user));
            }
        }
        return usersModel;
    }

}