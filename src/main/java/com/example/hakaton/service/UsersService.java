package com.example.hakaton.service;

import com.example.hakaton.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {
    void create(User user, MultipartFile photo);
    void createUser(User user, MultipartFile photo);

    void update(User user, MultipartFile file);

    void updateProfile(User user, MultipartFile file);

    User findById(Long id);
    User getMyProfile();

    List<User> findList();

    void delete(Long id);

    Long getAuthorizedUserId();
}
