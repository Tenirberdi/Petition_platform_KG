package com.example.hakaton.repository;

import com.example.hakaton.entity.PetitionEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetitionsRepository extends JpaRepository <PetitionEntity,Long> {
    @Query(value = "SELECT SUM(DISTINCT user_id) FROM petitions_users WHERE petition_id = ?1 group by user_id", nativeQuery = true)
    List<Long> getPetitionVotes(Long id);

    @Modifying
    @Query(value = "INSERT INTO public.petitions_users(petition_id, user_id) VALUES (?1, ?2);", nativeQuery = true)
    void vote(Long petitionId, Long userId);

    @Query(value = "SELECT * FROM public.petitions where author_id = ?1", nativeQuery = true)
    List<PetitionEntity> findMyPetitions(Long id);
}
