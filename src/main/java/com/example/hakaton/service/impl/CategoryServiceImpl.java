package com.example.hakaton.service.impl;

import com.example.hakaton.entity.Category;
import com.example.hakaton.entity.Users;
import com.example.hakaton.exception.CustomException;
import com.example.hakaton.exception.LicenseSeriesException;
import com.example.hakaton.mapper.CategoryMapper;
import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.response.CategoryResponse;
import com.example.hakaton.repository.CategoryRepository;
import com.example.hakaton.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;


    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category entity = categoryMapper.requestToEntity(request);
        Category savedEntity = categoryRepository.save(entity);
        return categoryMapper.entityToResponse(savedEntity);
    }

    @Override
    public CategoryResponse update(CategoryRequest request, Long id) {
        Category entity = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        categoryMapper.update(entity, request);
        Category savedEntity = categoryRepository.save(entity);
        return categoryMapper.entityToResponse(savedEntity);
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category entity = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        Category savedEntity = categoryRepository.save(entity);
        return categoryMapper.entityToResponse(savedEntity);
    }

    @Override
    public List<CategoryResponse> findList() {
        List<Category> entityList = categoryRepository.findAll();
        return entityList.stream()
                .map(categoryMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Category entity = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        categoryRepository.delete(entity);
    }
}
