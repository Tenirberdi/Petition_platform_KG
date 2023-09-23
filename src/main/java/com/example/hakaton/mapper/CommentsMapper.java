package com.example.hakaton.mapper;

import com.example.hakaton.entity.Category;
import com.example.hakaton.entity.Comments;
import com.example.hakaton.entity.Petitions;
import com.example.hakaton.model.request.CategoryRequest;
import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.response.CategoryResponse;
import com.example.hakaton.model.response.CommentsResponse;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CommentsMapper {
    CommentsResponse entityToResponse(Comments entity);
    @Mapping(target = "petitions", source = "petitionsId", qualifiedByName = "SetPetitions")
    Comments requestToEntity(CommentsRequest request);
    @Mapping(target = "petitions", source = "petitionsId", qualifiedByName = "SetPetitions")
    Comments update(@MappingTarget Comments entity, CommentsRequest request);


    @Named("SetPetitions")
    default Petitions toPetitions(Long id){
        if (id == null){
            return null;
        }
        return Petitions.builder().id(id).build();
    }
}
