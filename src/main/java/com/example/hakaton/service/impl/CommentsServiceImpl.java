package com.example.hakaton.service.impl;

import com.example.hakaton.entity.Comments;
import com.example.hakaton.entity.Users;
import com.example.hakaton.exception.CustomException;
import com.example.hakaton.exception.LicenseSeriesException;
import com.example.hakaton.mapper.CommentsMapper;
import com.example.hakaton.model.request.CommentsRequest;
import com.example.hakaton.model.response.CommentsResponse;
import com.example.hakaton.repository.CommentsRepository;
import com.example.hakaton.service.CommentsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    CommentsMapper commentsMapper;

    CommentsRepository commentsRepository;
    @Override
    public CommentsResponse create(CommentsRequest request) {
        Comments entity = commentsMapper.requestToEntity(request);
        Comments savedEntity = commentsRepository.save(entity);
        return commentsMapper.entityToResponse(savedEntity);
    }

    @Override
    public CommentsResponse update(CommentsRequest request, Long id) {
        Comments entity = commentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        commentsMapper.update(entity, request);
        Comments savedEntity = commentsRepository.save(entity);
        return commentsMapper.entityToResponse(savedEntity);
    }

    @Override
    public CommentsResponse findById(Long id) {
        Comments entity = commentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        Comments savedEntity = commentsRepository.save(entity);
        return commentsMapper.entityToResponse(savedEntity);
    }

    @Override
    public List<CommentsResponse> findList() {
        List<Comments> entityList = commentsRepository.findAll();
        return entityList.stream()
                .map(commentsMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Comments entity = commentsRepository.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        commentsRepository.delete(entity);
    }
}
