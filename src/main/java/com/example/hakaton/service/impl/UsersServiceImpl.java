package com.example.hakaton.service.impl;

import com.example.hakaton.entity.Users;
import com.example.hakaton.exception.CustomException;
import com.example.hakaton.exception.LicenseSeriesException;
import com.example.hakaton.mapper.UsersMapper;
import com.example.hakaton.model.request.UsersRequest;
import com.example.hakaton.model.response.UsersResponse;
import com.example.hakaton.repository.UsersRepisotory;
import com.example.hakaton.service.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    UsersRepisotory usersRepisotory;

    UsersMapper usersMapper;


    @Override
    public UsersResponse create(UsersRequest request) {
        Users entity = usersMapper.requestToEntity(request);
        Users savedEntity = usersRepisotory.save(entity);
        return usersMapper.entityToResponse(savedEntity);
    }

    @Override
    public UsersResponse update(UsersRequest request, Long id) {
        Users entity = usersRepisotory.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        usersMapper.update(entity, request);
        Users savedEntity = usersRepisotory.save(entity);
        return usersMapper.entityToResponse(savedEntity);
    }

    @Override
    public UsersResponse findById(Long id) {
        Users entity = usersRepisotory.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        Users savedEntity = usersRepisotory.save(entity);
        return usersMapper.entityToResponse(savedEntity);
    }

    @Override
    public List<UsersResponse> findList() {
        List<Users> entityList = usersRepisotory.findAll();
        return entityList.stream()
                .map(usersMapper::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Users entity = usersRepisotory.findById(id)
                .orElseThrow(() -> new CustomException(LicenseSeriesException.LICENSE_SERIES_EXCEPTION_NOT_FOUND));
        usersRepisotory.delete(entity);

    }
}
