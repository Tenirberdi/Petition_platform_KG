package com.example.hakaton.model.response;

import com.example.hakaton.entity.Category;
import com.example.hakaton.entity.Petitions;
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
public class PetitionsResponse {
    String name;
    String zagolovok;
    String telo;
    Category category;

    List<Petitions> systemPartId;
}
