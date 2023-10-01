package com.example.hakaton.service.impl;

import com.example.hakaton.entity.FileEntity;
import com.example.hakaton.entity.RoleEntity;
import com.example.hakaton.entity.UserEntity;
import com.example.hakaton.exception.NotFoundException;
import com.example.hakaton.model.User;
import com.example.hakaton.repository.RoleRepository;
import com.example.hakaton.repository.UsersRepisotory;
import com.example.hakaton.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

import static com.example.hakaton.Utils.Converter.toEntity;
import static com.example.hakaton.Utils.Converter.toModel;
import static com.example.hakaton.Utils.FileUtil.storePhoto;
import static com.example.hakaton.model.Constants.USER_ROLE;


@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    @PersistenceContext
    private EntityManager em;
    private final UsersRepisotory usersRepisotory;
    private final RoleRepository roleRepository;


    @Override
    @PreAuthorize("hasAuthority('user.create')")
    public void create(User user, MultipartFile photo) {
        UserEntity userEntity = toEntity(user);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        RoleEntity roleEntity = roleRepository.findById(user.getRoleId().getId())
                .orElseThrow(() -> new NotFoundException(String.format("Role with id %d not found", user.getRoleId().getId())));
        userEntity.setRole(roleEntity);
        if (photo != null && photo.getSize() != 0) {
            String path = storePhoto(photo);
            FileEntity fileEntity = FileEntity.builder()
                    .fileName(photo.getOriginalFilename())
                    .locationPath(path).build();

            userEntity.setProfilePhoto(fileEntity);
        }
        usersRepisotory.save(userEntity);
    }


    @Override
    public void createUser(User user, MultipartFile photo) {
        UserEntity userEntity = toEntity(user);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        RoleEntity roleEntity = roleRepository.findByName(USER_ROLE);
        userEntity.setRole(roleEntity);
        if (photo != null && photo.getSize() != 0) {

            String path = storePhoto(photo);
            FileEntity fileEntity = FileEntity.builder()
                    .fileName(photo.getOriginalFilename())
                    .locationPath(path).build();

            userEntity.setProfilePhoto(fileEntity);
        }
        usersRepisotory.save(userEntity);
    }



    @Override
    @PreAuthorize("hasAuthority('user.update')")
    public void update(User user, MultipartFile photo) {
        if(user.getId() != null) {
            UserEntity userEntity = usersRepisotory.findById(user.getId())
                    .orElseThrow(() -> new NotFoundException("User not found with id: " + user.getId()));
            userEntity.setLastName(user.getLastName());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setPatrName(user.getPatrName());
            userEntity.setUsername(user.getUsername());
            userEntity.setPassword(user.getPassword());


            if (photo != null && photo.getSize() != 0) {
                String path = storePhoto(photo);
                FileEntity fileEntity = FileEntity.builder()
                        .fileName(photo.getOriginalFilename())
                        .locationPath(path).build();

                userEntity.setProfilePhoto(fileEntity);
            }

            usersRepisotory.save(userEntity);
        }
    }

    @Override
    public User findById(Long id) {
        return toModel(usersRepisotory.findById(id).get());
    }

    @Override
    @PreAuthorize("hasAuthority('user.read')")
    public User getMyProfile() {
        return toModel(usersRepisotory.findById(getAuthorizedUserId()).get());
    }

    @Override
    public List<User> findList() {
        return null;
    }

    @Override
    @PreAuthorize("hasAuthority('user.delete')")
    public void delete(Long id) {

    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Long getAuthorizedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserEntity user = usersRepisotory.findByUsername(userDetails.getUsername());
            em.detach(user);
            user.setPassword("");
            return toModel(user).getId();
        } else {
            return null;
        }
    }
}
