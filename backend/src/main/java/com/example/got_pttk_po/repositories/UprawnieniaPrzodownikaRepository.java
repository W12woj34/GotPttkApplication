package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.UprawnieniaPrzodownikaEntity;
import com.example.got_pttk_po.entities.UprawnieniaPrzodownikaEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UprawnieniaPrzodownikaRepository extends JpaRepository<UprawnieniaPrzodownikaEntity, UprawnieniaPrzodownikaEntityPK> {

    List<UprawnieniaPrzodownikaEntity> findByGrupaIn(Collection<String> groups);
}