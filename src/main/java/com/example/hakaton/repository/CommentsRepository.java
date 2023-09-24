package com.example.hakaton.repository;

import com.example.hakaton.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentEntity,Long> {
}
