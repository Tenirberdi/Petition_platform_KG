package com.example.hakaton.model.response;

import com.example.hakaton.entity.PetitionEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentsResponse {
    String text;
    PetitionEntity petitionEntity;
}
