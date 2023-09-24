package com.example.hakaton.repository;

import com.example.hakaton.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepisotory extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
}
