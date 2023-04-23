package com.example.hakaton.service;

import com.example.hakaton.entity.TrashCanStoryEntity;
import com.example.hakaton.entity.UserStoryEntity;
import com.example.hakaton.repository.TrashCanStoryRepository;
import com.example.hakaton.repository.UserStoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryService {

    private final UserStoryRepository userStoryRepository;
    private final TrashCanStoryRepository trashCanStoryRepository;

    public StoryService(UserStoryRepository userStoryRepository,TrashCanStoryRepository trashCanStoryRepository) {
        this.userStoryRepository = userStoryRepository;
        this.trashCanStoryRepository = trashCanStoryRepository;
    }

    public void createUserStory(Long userId, LocalDateTime date, String operation) {
        UserStoryEntity userStoryEntity = new UserStoryEntity();
        userStoryEntity.setUserId(userId);
        userStoryEntity.setDate(date);
        userStoryEntity.setOperation(operation);
        userStoryRepository.save(userStoryEntity);
    }
    public void createTrashCanStory(Long trashCanId, String email, LocalDateTime date, String operation) {
        TrashCanStoryEntity trashCanStoryEntity = new TrashCanStoryEntity();
        trashCanStoryEntity.setTrashCanId(trashCanId);
        trashCanStoryEntity.setUserEmail(email);
        trashCanStoryEntity.setDate(date);
        trashCanStoryEntity.setOperation(operation);
        trashCanStoryRepository.save(trashCanStoryEntity);
    }

    public List<UserStoryEntity> getUserStory(Long id) {
        return userStoryRepository.findAllByUserId(id);
    }

    public List<TrashCanStoryEntity> getTrashCanStory(Long id) {
        return trashCanStoryRepository.findAllByTrashCanId(id);
    }
}
