package com.example.hakaton.model.response;

import com.example.hakaton.entity.Petitions;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentsResponse {
    String name;
    Petitions petitions;
}
