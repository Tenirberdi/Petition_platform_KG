package com.example.hakaton.model;

import com.example.hakaton.entity.RoleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    String firstName;
    String lastName;
    String patrName;
    String username;
    String password;
    long inn;
    Role roleId;
    File profilePhotoId;
    LocalDateTime createdAt;
}
