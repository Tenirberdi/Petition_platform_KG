package com.example.hakaton.service.impl;

import com.example.hakaton.Utils.Converter;
import com.example.hakaton.entity.CategoryEntity;
import com.example.hakaton.entity.FileEntity;
import com.example.hakaton.entity.PetitionEntity;
import com.example.hakaton.exception.NotFoundException;
import com.example.hakaton.model.Petition;
import com.example.hakaton.repository.CategoryRepository;
import com.example.hakaton.repository.PetitionsRepository;
import com.example.hakaton.repository.UsersRepisotory;
import com.example.hakaton.service.PetitionsService;
import com.example.hakaton.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.example.hakaton.Utils.Converter.*;
import static com.example.hakaton.Utils.FileUtil.storePhoto;
import static com.example.hakaton.Utils.FileUtil.updatePhoto;

@Service
@RequiredArgsConstructor
public class PetitionServiceImpl implements PetitionsService {
    private final PetitionsRepository petitionsRepository;
    private final UsersRepisotory usersRepisotory;
    private final CategoryRepository categoryRepository;
    private final UsersService usersService;
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('petition.create')")
    public Long createPetition(Petition petition, MultipartFile photo) {
        CategoryEntity categoryEntity = categoryRepository.findById(petition.getCategory().getId()).get();
        PetitionEntity petitionEntity = toEntity(petition);
        if (photo != null && photo.getSize() != 0) {
            String path = storePhoto(photo);
            FileEntity fileEntity = FileEntity.builder()
                    .fileName(photo.getOriginalFilename())
                    .locationPath(path).build();
            petitionEntity.setPhoto(fileEntity);
        }

        petitionEntity.setCategoryEntity(categoryEntity);
        petitionEntity.setAuthor(usersRepisotory.findById(usersService.getAuthorizedUserId()).get());
        return petitionsRepository.save(petitionEntity).getId();
    }

    @Override
    @PreAuthorize("hasAuthority('petition.update')")
    public void updatePetition(Petition petition, MultipartFile photo) {
        if(petition.getId() != null) {
            PetitionEntity petitionEntity = petitionsRepository.findById(petition.getId())
                    .orElseThrow(() -> new NotFoundException("Petition not found with id: " + petition.getId()));
            petitionEntity.setTitle(petition.getTitle());
            petitionEntity.setBody(petition.getBody());
            petitionEntity.setCategoryEntity(categoryRepository.findById(petition.getCategory().getId()).get());

            if (photo != null && photo.getSize() != 0) {
                if(petitionEntity.getPhoto() != null ){
                    updatePhoto(photo, toModel(petitionEntity.getPhoto()));
                } else {
                    String path = storePhoto(photo);
                    FileEntity fileEntity = FileEntity.builder()
                            .fileName(photo.getOriginalFilename())
                            .locationPath(path).build();
                    petitionEntity.setPhoto(fileEntity);
                }

            }

            petitionsRepository.save(petitionEntity);
        }
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void updateOwnPetition(Petition petition, MultipartFile photo) {
        if(petition.getId() != null) {
            PetitionEntity petitionEntity = petitionsRepository.findById(petition.getId())
                    .orElseThrow(() -> new NotFoundException("Petition not found with id: " + petition.getId()));
            if (Objects.equals(petitionEntity.getAuthor().getId(), usersService.getAuthorizedUserId())) {
                petitionEntity.setTitle(petition.getTitle());
                petitionEntity.setBody(petition.getBody());
                petitionEntity.setCategoryEntity(categoryRepository.findById(petition.getCategory().getId()).get());

                if (photo != null && photo.getSize() != 0) {
                    if(petitionEntity.getPhoto() != null ){
                        updatePhoto(photo, toModel(petitionEntity.getPhoto()));
                    } else {
                        String path = storePhoto(photo);
                        FileEntity fileEntity = FileEntity.builder()
                                .fileName(photo.getOriginalFilename())
                                .locationPath(path).build();
                        petitionEntity.setPhoto(fileEntity);
                    }

                }

                petitionsRepository.save(petitionEntity);
            }
        }
    }

    @Override
    @PreAuthorize("hasAuthority('petition.delete')")
    public void deletePetition(long id) {
        petitionsRepository.deleteById(id);
    }

    @Override
    public Petition findPetitionById(long id) {
        return toModel(petitionsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Petition not found with id: " + id)));
    }

    @Override
    public List<Petition> findPetitions(Pageable pageable) {
        List<Petition> petitions = new ArrayList<>();
        petitionsRepository.findAll(pageable).stream().map(Converter::toModel).forEach(petitions::add);

        return petitions;
    }

    @Override
    @PreAuthorize("hasAuthority('petition.read')")
    public List<Petition> findMyPetitions() {
        List<Petition> petitions = new ArrayList<>();
        petitionsRepository.findMyPetitions(usersService.getAuthorizedUserId())
                .stream().map(Converter::toModel).forEach(petitions::add);

        return petitions;
    }

    @Override
    public Integer getPetitionVotes(Long id) {
        return petitionsRepository.getPetitionVotes(id).size();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('petition.vote')")
    public void vote(Long id) {
        petitionsRepository.vote(id, usersService.getAuthorizedUserId());
    }
}
