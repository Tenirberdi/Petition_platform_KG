package com.example.hakaton.service;

import com.example.hakaton.model.Tender;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TendersService {
    Long createTender(Tender tender, MultipartFile photo);
    void updateTender(Tender tender, MultipartFile file);
    void updateOwnTender(Tender tender, MultipartFile file);
    void deleteTender(long id);
    Tender findTenderById(long id);
    //add filter
    List<Tender> findTenders(Pageable pageable);
    List<Tender> findMyTenders();
    Integer getTenderVotes(Long id);
    void vote(Long id);
}
