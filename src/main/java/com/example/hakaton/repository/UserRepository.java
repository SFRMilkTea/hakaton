package com.example.hakaton.repository;

import com.example.hakaton.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
   UserEntity findByFirstName (String firstName);
   UserEntity findByEmail (String email);
}