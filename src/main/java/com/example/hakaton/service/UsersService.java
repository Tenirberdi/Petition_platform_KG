package com.example.hakaton.service;

import com.example.hakaton.model.Petition;
import com.example.hakaton.model.User;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.UsersResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UsersService {
    void create(User user, MultipartFile photo);

    void updateUser(User user, MultipartFile file);

    void update(User user, Long id);

    User findById(Long id);

    List<User> findList();

    void delete(Long id);

    Long getAuthorizedUserId();
}
