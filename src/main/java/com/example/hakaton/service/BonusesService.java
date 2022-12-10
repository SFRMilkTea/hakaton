package com.example.hakaton.service;

import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.entity.UserStoryEntity;
import com.example.hakaton.exception.NotEnoughBonusesException;
import com.example.hakaton.exception.UserNotFoundException;
import com.example.hakaton.repository.UserRepository;
import com.example.hakaton.repository.UserStoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BonusesService {

    private final UserRepository userRepository;
    private final UserStoryRepository userStoryRepository;

    public BonusesService(UserRepository userRepository, UserStoryRepository userStoryRepository) {
        this.userRepository = userRepository;
        this.userStoryRepository = userStoryRepository;
    }

    public void addBonuses(int bonuses, Long id) throws UserNotFoundException {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + id + " не существует"));
        user.setBonuses(user.getBonuses() + bonuses);
        userRepository.save(user);
        UserStoryEntity userStoryEntity = new UserStoryEntity();
        userStoryEntity.setUserId(id);
        userStoryEntity.setDate(LocalDateTime.now());
        userStoryEntity.setOperation("Начислено " + bonuses + " бонусов");
        userStoryRepository.save(userStoryEntity);
    }

    public void useBonuses(int bonuses,Long id) throws UserNotFoundException, NotEnoughBonusesException {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + id + " не существует"));
        if (user.getBonuses()-bonuses<0)
            throw new NotEnoughBonusesException("Недостаточно бонусов. Доступно бонусов для списания: " + user.getBonuses());
        user.setBonuses(user.getBonuses()-bonuses);
        userRepository.save(user);
        UserStoryEntity userStoryEntity = new UserStoryEntity();
        userStoryEntity.setUserId(id);
        userStoryEntity.setDate(LocalDateTime.now());
        userStoryEntity.setOperation("Списано " + bonuses + " бонусов");
        userStoryRepository.save(userStoryEntity);
    }
}
