package com.example.hakaton.service;

import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.CategoryResponse;
import com.example.hakaton.model.response.UsersResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(CategoryRequest request, Long id);

    CategoryResponse findById(Long id);

    List<CategoryResponse> findList();

    void delete(Long id);
}
