package com.example.hakaton.Utils;

import com.example.hakaton.entity.CategoryEntity;
import com.example.hakaton.entity.CommentEntity;
import com.example.hakaton.entity.FileEntity;
import com.example.hakaton.entity.PermissionEntity;
import com.example.hakaton.entity.TenderEntity;
import com.example.hakaton.entity.RoleEntity;
import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.model.Category;
import com.example.hakaton.model.Comment;
import com.example.hakaton.model.File;
import com.example.hakaton.model.Permission;
import com.example.hakaton.model.Tender;
import com.example.hakaton.model.Role;
import com.example.hakaton.model.User;

public class Converter {
    public static Tender toModel(TenderEntity petitionEntity) {
        if (petitionEntity == null) {
            return null;
        }
        return Tender.builder()
                .id(petitionEntity.getId())
                .title(petitionEntity.getTitle())
                .body(petitionEntity.getBody())
                .category(toModel(petitionEntity.getCategoryEntity()))
                .createdAt(petitionEntity.getCreatedAt())
                .author(toModel(petitionEntity.getAuthor()))
                .photo(toModel(petitionEntity.getPhoto())).build();
    }

    public static TenderEntity toEntity(Tender petition) {
        if (petition == null) {
            return null;
        }
        return TenderEntity.builder()
                .id(petition.getId())
                .title(petition.getTitle())
                .body(petition.getBody())
                .categoryEntity(toEntity(petition.getCategory()))
                .author(toEntity(petition.getAuthor()))
                .createdAt(petition.getCreatedAt())
                .photo(toEntity(petition.getPhoto())).build();
    }

    public static File toModel(FileEntity file) {
        if (file == null) {
            return null;
        }
        return File.builder()
                .id(file.getId())
                .fileName(file.getFileName())
                .locationPath(file.getLocationPath()).build();
    }

    public static FileEntity toEntity(File file) {
        if (file == null) {
            return null;
        }
        return FileEntity.builder()
                .id(file.getId())
                .fileName(file.getFileName())
                .locationPath(file.getLocationPath()).build();
    }

    public static Category toModel(CategoryEntity category) {
        if (category == null) {
            return null;
        }
        return Category.builder()
                .id(category.getId())
                .name(category.getName()).build();
    }

    public static CategoryEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName()).build();
    }

    public static Role toModel(RoleEntity role) {
        if (role == null) {
            return null;
        }
        return Role.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription()).build();
    }

    public static RoleEntity toEntity(Role role) {
        if (role == null) {
            return null;
        }
        return RoleEntity.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription()).build();
    }

    public static Permission toModel(PermissionEntity permission) {
        if (permission == null) {
            return null;
        }
        return Permission.builder()
                .name(permission.getName())
                .description(permission.getDescription()).build();
    }

    public static PermissionEntity toEntity(Permission permission) {
        if (permission == null) {
            return null;
        }
        return PermissionEntity.builder()
                .name(permission.getName())
                .description(permission.getDescription()).build();
    }

    public static Comment toModel(CommentEntity comment) {
        if (comment == null) {
            return null;
        }
        return Comment.builder()
                .id(comment.getId())
                .text(comment.getText())
                .tender(toModel(comment.getTenderEntity()))
                .author(toModel(comment.getAuthor())).build();
    }

    public static CommentEntity toEntity(Comment comment) {
        if (comment == null) {
            return null;
        }
        return CommentEntity.builder()
                .id(comment.getId())
                .text(comment.getText())
                .tenderEntity(toEntity(comment.getTender()))
                .author(toEntity(comment.getAuthor())).build();
    }

    public static User toModel(UserEntity user) {
        if (user == null) {
            return null;
        }
        return User.builder()
                .id(user.getId())
                .inn(user.getInn())
                .createdAt(user.getCreatedAt())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patrName(user.getPatrName())
                .roleId(toModel(user.getRole()))
                .username(user.getUsername())
                .profilePhotoId(toModel(user.getProfilePhoto())).build();
    }

    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        return UserEntity.builder()
                .id(user.getId())
                .inn(user.getInn())
                .createdAt(user.getCreatedAt())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .patrName(user.getPatrName())
                .role(toEntity(user.getRoleId()))
                .profilePhoto(toEntity(user.getProfilePhotoId())).build();
    }

}
