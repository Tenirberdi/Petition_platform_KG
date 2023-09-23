package com.example.hakaton.repository;

import com.example.hakaton.entity.Petitions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetitionsRepository extends JpaRepository <Petitions,Long> {
}
