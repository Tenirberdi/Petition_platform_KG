package com.example.hakaton.repository;

import com.example.hakaton.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity,String> {
}
