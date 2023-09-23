package com.example.hakaton.service;

import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.CommentsResponse;
import com.example.hakaton.model.response.UsersResponse;

import java.util.List;

public interface CommentsService {
    CommentsResponse create(CommentsRequest request);

    CommentsResponse update(CommentsRequest request, Long id);

    CommentsResponse findById(Long id);

    List<CommentsResponse> findList();

    void delete(Long id);
}
