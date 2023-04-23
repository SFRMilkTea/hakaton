package com.example.hakaton.repository;

import com.example.hakaton.entity.TrashCanStoryEntity;
import com.example.hakaton.entity.UserStoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrashCanStoryRepository extends CrudRepository<TrashCanStoryEntity, Long> {
    List<TrashCanStoryEntity> findAllByTrashCanId(Long trashCanId);
}