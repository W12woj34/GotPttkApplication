package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.OdznakaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OdznakaRepository extends JpaRepository<OdznakaEntity, String> {

    List<OdznakaEntity> findByNazwaIn(Collection<String> ids);
}