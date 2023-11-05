package com.example.hakaton.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TendersRequest {
    String name;
    String title;
    String body;
    Long categoryId;
    List<Long> systemPartId;
}
