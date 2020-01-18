package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.PodgrupaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PodgrupaRepository extends JpaRepository<PodgrupaEntity, String> {

    List<PodgrupaEntity> findByGrupa(String grupa);
    List<PodgrupaEntity> findByIdIn(List<String> ids);
}