package com.example.hakaton.service.impl;

import com.example.hakaton.Utils.Converter;
import com.example.hakaton.entity.CategoryEntity;
import com.example.hakaton.entity.FileEntity;
import com.example.hakaton.entity.TenderEntity;
import com.example.hakaton.exception.NotFoundException;
import com.example.hakaton.model.Tender;
import com.example.hakaton.repository.CategoryRepository;
import com.example.hakaton.repository.TenderRepository;
import com.example.hakaton.repository.UsersRepisotory;
import com.example.hakaton.service.TendersService;
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

import static com.example.hakaton.Utils.Converter.*;
import static com.example.hakaton.Utils.FileUtil.storePhoto;
import static com.example.hakaton.Utils.FileUtil.updatePhoto;

@Service
@RequiredArgsConstructor
public class TenderServiceImpl implements TendersService {
    private final TenderRepository tenderRepository;
    private final UsersRepisotory usersRepisotory;
    private final CategoryRepository categoryRepository;
    private final UsersService usersService;
    @Override
    @Transactional
    @PreAuthorize("hasAuthority('tender.create')")
    public Long createTender(Tender tender, MultipartFile photo) {
        CategoryEntity categoryEntity = categoryRepository.findById(tender.getCategory().getId()).get();
        TenderEntity tenderEntity = toEntity(tender);
        if (photo != null && photo.getSize() != 0) {
            String path = storePhoto(photo);
            FileEntity fileEntity = FileEntity.builder()
                    .fileName(photo.getOriginalFilename())
                    .locationPath(path).build();
            tenderEntity.setPhoto(fileEntity);
        }

        tenderEntity.setCategoryEntity(categoryEntity);
        tenderEntity.setAuthor(usersRepisotory.findById(usersService.getAuthorizedUserId()).get());
        return tenderRepository.save(tenderEntity).getId();
    }

    @Override
    @PreAuthorize("hasAuthority('tender.update')")
    public void updateTender(Tender tender, MultipartFile photo) {
        if(tender.getId() != null) {
            TenderEntity tenderEntity = tenderRepository.findById(tender.getId())
                    .orElseThrow(() -> new NotFoundException("tender not found with id: " + tender.getId()));
            tenderEntity.setTitle(tender.getTitle());
            tenderEntity.setBody(tender.getBody());
            tenderEntity.setCategoryEntity(categoryRepository.findById(tender.getCategory().getId()).get());

            if (photo != null && photo.getSize() != 0) {
                if(tenderEntity.getPhoto() != null ){
                    updatePhoto(photo, toModel(tenderEntity.getPhoto()));
                } else {
                    String path = storePhoto(photo);
                    FileEntity fileEntity = FileEntity.builder()
                            .fileName(photo.getOriginalFilename())
                            .locationPath(path).build();
                    tenderEntity.setPhoto(fileEntity);
                }

            }

            tenderRepository.save(tenderEntity);
        }
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void updateOwnTender(Tender tender, MultipartFile photo) {
        if(tender.getId() != null) {
            TenderEntity tenderEntity = tenderRepository.findById(tender.getId())
                    .orElseThrow(() -> new NotFoundException("tender not found with id: " + tender.getId()));
            if (Objects.equals(tenderEntity.getAuthor().getId(), usersService.getAuthorizedUserId())) {
                tenderEntity.setTitle(tender.getTitle());
                tenderEntity.setBody(tender.getBody());
                tenderEntity.setCategoryEntity(categoryRepository.findById(tender.getCategory().getId()).get());

                if (photo != null && photo.getSize() != 0) {
                    if(tenderEntity.getPhoto() != null ){
                        updatePhoto(photo, toModel(tenderEntity.getPhoto()));
                    } else {
                        String path = storePhoto(photo);
                        FileEntity fileEntity = FileEntity.builder()
                                .fileName(photo.getOriginalFilename())
                                .locationPath(path).build();
                        tenderEntity.setPhoto(fileEntity);
                    }

                }

                tenderRepository.save(tenderEntity);
            }
        }
    }

    @Override
    @PreAuthorize("hasAuthority('tender.delete')")
    public void deleteTender(long id) {
        tenderRepository.deleteById(id);
    }

    @Override
    public Tender findTenderById(long id) {
        return toModel(tenderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tender not found with id: " + id)));
    }

    @Override
    public List<Tender> findTenders(Pageable pageable) {
        List<Tender> tenders = new ArrayList<>();
        tenderRepository.findAll(pageable).stream().map(Converter::toModel).forEach(tenders::add);

        return tenders;
    }

    @Override
    @PreAuthorize("hasAuthority('tender.read')")
    public List<Tender> findMyTenders() {
        List<Tender> tenders = new ArrayList<>();
        tenderRepository.findMyTenders(usersService.getAuthorizedUserId())
                .stream().map(Converter::toModel).forEach(tenders::add);

        return tenders;
    }

    @Override
    public Integer getTenderVotes(Long id) {
        return tenderRepository.getTenderVotes(id).size();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('tender.vote')")
    public void vote(Long id) {
        tenderRepository.vote(id, usersService.getAuthorizedUserId());
    }
}
