package com.example.hakaton.repository;

import com.example.hakaton.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface TrashCanRepository extends CrudRepository<UserEntity, Long> {

}