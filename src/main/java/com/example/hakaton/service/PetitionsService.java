package com.example.hakaton.service;

import com.example.hakaton.model.Petition;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PetitionsService {
    Long createPetition(Petition petition, MultipartFile photo);
    void updatePetition(Petition petition, MultipartFile file);
    void deletePetition(long id);
    Petition findPetitionById(long id);
    //add filter
    List<Petition> findPetitions();
    List<Petition> findMyPetitions();
    Integer getPetitionVotes(Long id);
    void vote(Long id);
}
