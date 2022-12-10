package com.example.hakaton.service;

import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.exception.UserNotAdminException;
import com.example.hakaton.exception.UserNotFoundException;
import com.example.hakaton.exception.WrongPasswordException;
import com.example.hakaton.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkAdminEmail(String email, String password) throws UserNotFoundException, UserNotAdminException, WrongPasswordException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с email " + email + " не существует");
        }
        if (!user.isAdmin()) {
            throw new UserNotAdminException("Недостаточно прав доступа");
        }
        checkPassword(email, password);
    }

    public void checkEmail(String email, String password) throws UserNotFoundException, WrongPasswordException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("Пользователь с email " + email + " не существует");
        }
        checkPassword(email, password);
    }

    public void checkPassword(String email, String password) throws WrongPasswordException {

        UserEntity user = userRepository.findByEmail(email);
        if (user.getPassword() != password)
            throw new WrongPasswordException("Неверный пароль");

    }
}

