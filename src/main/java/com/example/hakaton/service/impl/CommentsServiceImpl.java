package com.example.hakaton.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CommentsServiceImpl  {
//    CommentsMapper commentsMapper;
//
//    CommentsRepository commentsRepository;
//    @Override
//    public CommentsResponse create(CommentsRequest request) {
//        CommentsEntity entity = commentsMapper.requestToEntity(request);
//        CommentsEntity savedEntity = commentsRepository.save(entity);
//        return commentsMapper.entityToResponse(savedEntity);
//    }
//
//    @Override
//    public CommentsResponse update(CommentsRequest request, Long id) {
//        CommentsEntity entity = commentsRepository.findById(id)
//                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
//        commentsMapper.update(entity, request);
//        CommentsEntity savedEntity = commentsRepository.save(entity);
//        return commentsMapper.entityToResponse(savedEntity);
//    }
//
//    @Override
//    public CommentsResponse findById(Long id) {
//        CommentsEntity entity = commentsRepository.findById(id)
//                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
//        CommentsEntity savedEntity = commentsRepository.save(entity);
//        return commentsMapper.entityToResponse(savedEntity);
//    }
//
//    @Override
//    public List<CommentsResponse> findList() {
//        List<CommentsEntity> entityList = commentsRepository.findAll();
//        return entityList.stream()
//                .map(commentsMapper::entityToResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void delete(Long id) {
//        CommentsEntity entity = commentsRepository.findById(id)
//                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
//        commentsRepository.delete(entity);
//    }
}
