package com.example.hakaton.repository;

import com.example.hakaton.entity.PetitionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetitionsRepository extends JpaRepository <PetitionEntity, Long> {
    @Query(value = "SELECT SUM(DISTINCT user_id) FROM petitions_users WHERE petition_id = ?1 group by user_id", nativeQuery = true)
    List<Long> getPetitionVotes(Long id);

    @Modifying
    @Query(value = "INSERT INTO public.petitions_users(petition_id, user_id)\n" +
            "select ?1, ?2 WHERE NOT EXISTS (SELECT u FROM public.petitions_users as u\n" +
            " WHERE petition_id = ?1 AND user_id = ?2);", nativeQuery = true)
    void vote(Long petitionId, Long userId);

    @Query(value = "SELECT * FROM public.petitions where author_id = ?1", nativeQuery = true)
    List<PetitionEntity> findMyPetitions(Long id);

    Page<PetitionEntity> findAll(Pageable pageable);
}
