package com.example.hakaton.mapper;

import com.example.hakaton.entity.Category;
import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryResponse entityToResponse(Category entity);

    Category requestToEntity(CategoryRequest request);

    Category update(@MappingTarget Category entity, CategoryRequest request);


}
