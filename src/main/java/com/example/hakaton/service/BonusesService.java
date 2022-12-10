package com.example.hakaton.service;

import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.exception.UserNotFoundException;
import com.example.hakaton.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BonusesService {

    private final UserRepository userRepository;

    public BonusesService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addBonuses(int bonuses, Long id) throws UserNotFoundException {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + id + " не существует"));
        user.setBonuses(user.getBonuses() + bonuses);
        userRepository.save(user);
    }

    public void useBonuses(Long id) throws UserNotFoundException {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + id + " не существует"));
        user.setBonuses(0);
        userRepository.save(user);
    }
}
