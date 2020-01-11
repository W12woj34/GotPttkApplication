package com.example.got_pttk_po.repositories;

import com.example.got_pttk_po.entities.UzytkownikEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UzytkownikRepository extends JpaRepository<UzytkownikEntity, String> {

}

