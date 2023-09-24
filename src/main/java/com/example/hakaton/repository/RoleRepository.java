package com.example.hakaton.repository;

import com.example.hakaton.entity.PetitionEntity;
import com.example.hakaton.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity,String> {
}
