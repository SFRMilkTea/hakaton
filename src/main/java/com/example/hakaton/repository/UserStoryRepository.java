package com.example.hakaton.repository;

import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.entity.UserStoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserStoryRepository extends CrudRepository<UserStoryEntity, Long> {
    List<UserStoryEntity> findAllByUserId(Long userId);
}