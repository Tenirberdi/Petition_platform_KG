package com.example.hakaton.service;

import com.example.hakaton.model.Category;
import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    void create(Category category);

    void update(Category category);

    Category findById(Long id);

    List<Category> findAll();

    void delete(Long id);
}
