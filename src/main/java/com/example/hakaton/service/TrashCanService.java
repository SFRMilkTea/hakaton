package com.example.hakaton.service;

import com.example.hakaton.entity.TrashCanEntity;
import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.exception.TrashCanAlreadyExistException;
import com.example.hakaton.exception.TrashCanNotFoundException;
import com.example.hakaton.exception.UserAlreadyExistException;
import com.example.hakaton.exception.UserNotFoundException;
import com.example.hakaton.model.User;
import com.example.hakaton.repository.TrashCanRepository;
import com.example.hakaton.repository.TrashCanStoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrashCanService {

    private final TrashCanRepository trashCanRepository;

    public TrashCanService(TrashCanRepository trashCanRepository) {
        this.trashCanRepository = trashCanRepository;
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

}