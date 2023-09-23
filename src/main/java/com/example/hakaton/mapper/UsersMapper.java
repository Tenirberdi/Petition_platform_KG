package com.example.hakaton.mapper;

import com.example.hakaton.entity.Category;
import com.example.hakaton.entity.Users;
import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.CategoryResponse;
import com.example.hakaton.model.response.UsersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsersMapper {
    UsersResponse entityToResponse(Users entity);

    Users requestToEntity(UsersRequest request);

    Users update(@MappingTarget Users entity, UsersRequest request);
}
