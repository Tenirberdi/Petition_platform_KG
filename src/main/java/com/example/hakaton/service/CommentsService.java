package com.example.hakaton.service;

import com.example.hakaton.model.Comment;
import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.CommentsResponse;
import com.example.hakaton.model.response.UsersResponse;

import java.util.List;

public interface CommentsService {
    Comment create(Comment comment);
    List<Comment> findAllByPetitionId(Long petitionId);
}
