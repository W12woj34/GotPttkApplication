package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.GrupaGorskaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface GrupaGorskaRepository extends JpaRepository<GrupaGorskaEntity, String> {

    List<GrupaGorskaEntity> findByNazwaIn(Collection<String> ids);
}
