package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZdobywanaOdznakaRepository extends JpaRepository<ZdobywanaOdznakaEntity, Integer> {

    List<ZdobywanaOdznakaEntity> findByTurysta(String turysta);
    List<ZdobywanaOdznakaEntity> findByTurystaAndStatus(String turysta, Integer status);
}