package com.example.hakaton.service;

import com.example.hakaton.entity.TrashCanEntity;
import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.exception.NotEnoughBonusesException;
import com.example.hakaton.exception.TrashCanNotFoundException;
import com.example.hakaton.exception.UserNotFoundException;
import com.example.hakaton.repository.TrashCanRepository;
import com.example.hakaton.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BonusesService {

    private final UserRepository userRepository;
    private final TrashCanRepository trashCanRepository;
    private final StoryService storyService;

    public BonusesService(UserRepository userRepository, StoryService storyService,
                          TrashCanRepository trashCanRepository) {
        this.userRepository = userRepository;
        this.storyService = storyService;
        this.trashCanRepository = trashCanRepository;
    }

    public void addBonuses(int bonuses, Long userId, Long trashCanId) throws UserNotFoundException, TrashCanNotFoundException {
        UserEntity user = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + userId + " не существует"));
        TrashCanEntity trashCan = trashCanRepository
                .findById(trashCanId)
                .orElseThrow(() -> new TrashCanNotFoundException("Мусорный бак с id: " + userId + " не существует"));
        user.setBonuses(user.getBonuses() + bonuses);
        userRepository.save(user);
        String operation = "Начислено " + bonuses + " бонусов";
        storyService.createUserStory(userId, LocalDateTime.now(), operation);
        storyService.createTrashCanStory(trashCanId, user.getEmail(), LocalDateTime.now(), operation);
    }

    public void useBonuses(int bonuses, Long id) throws UserNotFoundException, NotEnoughBonusesException {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id: " + id + " не существует"));
        if (user.getBonuses() - bonuses < 0)
            throw new NotEnoughBonusesException("Недостаточно бонусов. Доступно бонусов для списания: " + user.getBonuses());
        user.setBonuses(user.getBonuses() - bonuses);
        userRepository.save(user);
        String operation = "Списано " + bonuses + " бонусов";
        storyService.createUserStory(id, LocalDateTime.now(), operation);
    }
}
