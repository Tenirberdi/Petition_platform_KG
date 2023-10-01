package com.example.hakaton.repository;

import com.example.hakaton.entity.CommentEntity;
import com.example.hakaton.entity.PetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByPetitionEntityId(Long id);
}
