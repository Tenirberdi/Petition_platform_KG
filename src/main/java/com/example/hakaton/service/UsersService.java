package com.example.hakaton.service;

import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.UsersResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsersService {
    UsersResponse create(UsersRequest request);

    UsersResponse update(UsersRequest request, Long id);

    UsersResponse findById(Long id);

    List<UsersResponse> findList();

    void delete(Long id);
}
