package com.example.hakaton.service;

import com.example.hakaton.entity.TrashCanEntity;
import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.exception.TrashCanAlreadyExistException;
import com.example.hakaton.exception.TrashCanNotFoundException;
import com.example.hakaton.repository.TrashCanRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrashCanService {

    private final TrashCanRepository trashCanRepository;
    private final StoryService storyService;

    public TrashCanService(TrashCanRepository trashCanRepository, StoryService storyService) {
        this.trashCanRepository = trashCanRepository;
        this.storyService = storyService;
    }

    public TrashCanEntity getTrashCan(Long id) throws TrashCanNotFoundException {
        TrashCanEntity trashCanEntity = trashCanRepository
                .findById(id)
                .orElseThrow(() -> new TrashCanNotFoundException("Мусорный контейнер с id: " + id + " не существует"));
        return trashCanEntity;
    }

    public List<TrashCanEntity> getTrashCans() {
        Iterable<TrashCanEntity> trashCanEntities = trashCanRepository.findAll();
        ArrayList<TrashCanEntity> trashCans = new ArrayList<>();
        for (TrashCanEntity trashCan : trashCanEntities) {
            trashCans.add(trashCan);
        }
        return trashCans;
    }

    public void delete(Long id) throws TrashCanNotFoundException {
        try {
            trashCanRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TrashCanNotFoundException("Мусорный бак с id: " + id + " не существует");
        }
    }

    public void addTrashCan(TrashCanEntity trashCanEntity) throws TrashCanAlreadyExistException {
        if (trashCanRepository.findByAddress(trashCanEntity.getAddress()) == null) {
            trashCanEntity.setGlassFullness((int) (Math.random() * 101));
            trashCanEntity.setPaperFullness((int) (Math.random() * 101));
            trashCanEntity.setPlasticFullness((int) (Math.random() * 101));
            trashCanRepository.save(trashCanEntity);
        } else
            throw new TrashCanAlreadyExistException("Мусорный бак по адресу " + trashCanEntity.getAddress() + " уже существует");
    }

    public List<TrashCanEntity> findByString(String str) {
        Iterable<TrashCanEntity> trashCansEntities = trashCanRepository.findAll();
        ArrayList<TrashCanEntity> trashCans = new ArrayList<>();
        for (TrashCanEntity trashCan : trashCansEntities) {
            if (trashCan.getAddress().toLowerCase().contains(str.toLowerCase())) {
                trashCans.add(trashCan);
            }
        }
        return trashCans;
    }

    public void setDisabled(Long id, boolean disabled) throws TrashCanNotFoundException {
        TrashCanEntity trashCanEntity = getTrashCan(id);
        trashCanEntity.setDisabled(disabled);
        trashCanRepository.save(trashCanEntity);
        if (disabled) {
            storyService.createTrashCanStory(id, "Администратор", LocalDateTime.now(), "Контейнер заблокирован");
        } else {
            storyService.createTrashCanStory(id, "Администратор", LocalDateTime.now(), "Контейнер разблокирован");
        }
    }
}