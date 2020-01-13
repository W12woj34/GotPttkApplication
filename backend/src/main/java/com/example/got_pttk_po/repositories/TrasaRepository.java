package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.TrasaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrasaRepository extends JpaRepository<TrasaEntity, Integer> {

    List<TrasaEntity> findByPoczatkowy(String poczatkowy);
    List<TrasaEntity> findByPodgrupa(String podgrupa);
}