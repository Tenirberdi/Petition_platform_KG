package com.example.hakaton.repository;

import com.example.hakaton.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentEntity,Long> {
    List<CommentEntity> findByTenderEntityId(Long id);
}
