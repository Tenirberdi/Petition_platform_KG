package com.example.hakaton.model.response;

import com.example.hakaton.entity.CategoryEntity;
import com.example.hakaton.entity.PetitionEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetitionsResponse {
    String name;
    String title;
    String body;
    CategoryEntity categoryEntity;

    List<PetitionEntity> systemPartId;
}
