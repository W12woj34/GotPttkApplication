package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.WycieczkaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface WycieczkaRepository extends JpaRepository<WycieczkaEntity, Integer> {

    List<WycieczkaEntity> findByPrzodownik(String leader);
    List<WycieczkaEntity> findByPrzodownikAndStatus(String leader, Integer status);
    List<WycieczkaEntity> findByOdznaka(Integer badge);
    List<WycieczkaEntity> findByOdznakaIn(Collection<Integer> badges);
}