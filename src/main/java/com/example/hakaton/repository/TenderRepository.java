package com.example.hakaton.repository;

import com.example.hakaton.entity.TenderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TenderRepository extends JpaRepository <TenderEntity, Long> {
    @Query(value = "SELECT SUM(DISTINCT user_id) FROM tenders_users WHERE tender_id = ?1 group by user_id", nativeQuery = true)
    List<Long> getTenderVotes(Long id);

    @Modifying
    @Query(value = "INSERT INTO public.tenders_users(tender_id, user_id)\n" +
            "select ?1, ?2 WHERE NOT EXISTS (SELECT u FROM public.tenders_users as u\n" +
            " WHERE tender_id = ?1 AND user_id = ?2);", nativeQuery = true)
    void vote(Long tenderId, Long userId);

    @Query(value = "SELECT * FROM public.tenders where author_id = ?1", nativeQuery = true)
    List<TenderEntity> findMyTenders(Long id);

    Page<TenderEntity> findAll(Pageable pageable);
}
