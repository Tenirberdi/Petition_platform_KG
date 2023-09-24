package com.example.hakaton.service.impl;

import com.example.hakaton.Utils.Converter;
import com.example.hakaton.model.Category;
import com.example.hakaton.repository.CategoryRepository;
import com.example.hakaton.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void create(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public Category findById(Long id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().stream().map(Converter::toModel).forEach(categories::add);
        return categories;
    }

    @Override
    public void delete(Long id) {

    }
}
