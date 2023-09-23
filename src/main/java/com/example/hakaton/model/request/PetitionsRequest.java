package com.example.hakaton.model.request;

import com.example.hakaton.entity.Category;
import com.example.hakaton.entity.Users;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetitionsRequest {
    String name;
    String zagolovok;
    String telo;
    Long categoryId;
    List<Long> systemPartId;
}
