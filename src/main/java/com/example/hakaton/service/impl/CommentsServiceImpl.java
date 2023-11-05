package com.example.hakaton.service.impl;

import com.example.hakaton.Utils.Converter;
import com.example.hakaton.entity.CommentEntity;
import com.example.hakaton.exception.NotFoundException;
import com.example.hakaton.model.Comment;
import com.example.hakaton.repository.CommentsRepository;
import com.example.hakaton.repository.TenderRepository;
import com.example.hakaton.repository.UsersRepisotory;
import com.example.hakaton.service.CommentsService;
import com.example.hakaton.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.hakaton.Utils.Converter.toEntity;
import static com.example.hakaton.Utils.Converter.toModel;


@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final CommentsRepository commentsRepository;
    private final UsersService usersService;
    private final UsersRepisotory usersRepisotory;
    private final TenderRepository tenderRepository;
    @Override
    @PreAuthorize("hasAuthority('comment.write')")
    public Comment create(Comment comment) {
        CommentEntity commentEntity = toEntity(comment);
        commentEntity.setAuthor(usersRepisotory
                .findById(usersService.getAuthorizedUserId()).get());
        commentEntity.setTenderEntity(tenderRepository.findById(comment.getTender().getId())
                .orElseThrow(() -> new NotFoundException(String.format("Petition with id %d not found", comment.getTender().getId()))));
        commentsRepository.save(commentEntity);
        return toModel(commentEntity);
    }

    @Override
    public List<Comment> findAllByTenderId(Long tenderId) {
        return commentsRepository.findByTenderEntityId(tenderId)
                .stream().map(Converter::toModel).collect(Collectors.toList());
    }
}
