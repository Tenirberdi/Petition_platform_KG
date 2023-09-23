package com.example.hakaton.model.request;

import com.example.hakaton.entity.Petitions;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentsRequest {
    String name;
    Long petitionsId;
}
