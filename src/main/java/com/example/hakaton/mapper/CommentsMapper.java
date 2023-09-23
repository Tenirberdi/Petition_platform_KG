package com.example.hakaton.mapper;

import com.example.hakaton.entity.Category;
import com.example.hakaton.entity.Comments;
import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.response.CategoryResponse;
import com.example.hakaton.model.response.CommentsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentsMapper {
    CommentsResponse entityToResponse(Comments entity);

    Comments requestToEntity(CommentsRequest request);

    Comments update(@MappingTarget Comments entity, CommentsRequest request);
}
