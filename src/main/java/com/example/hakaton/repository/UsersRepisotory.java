package com.example.hakaton.repository;

import com.example.hakaton.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepisotory extends JpaRepository<Users,Long> {
}
