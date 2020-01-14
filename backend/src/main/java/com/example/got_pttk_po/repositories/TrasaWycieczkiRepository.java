package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.TrasaWycieczkiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface TrasaWycieczkiRepository extends JpaRepository<TrasaWycieczkiEntity, Integer> {

    List<TrasaWycieczkiEntity> findByWycieczkaOrderByDataDesc(Integer id);
    List<TrasaWycieczkiEntity> findByWycieczkaIn(Collection<Integer> trips);
    List<TrasaWycieczkiEntity> findByNumerIn(Collection<Integer> ids);

    @Transactional
    void deleteByNumerIn(Collection<Integer> ids);

}