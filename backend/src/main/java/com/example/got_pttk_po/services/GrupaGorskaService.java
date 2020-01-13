package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.GrupaGorskaEntity;
import com.example.got_pttk_po.exceptions.GroupNotFoundException;
import com.example.got_pttk_po.repositories.GrupaGorskaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class GrupaGorskaService {

    private final GrupaGorskaRepository repositoryGrupaGorska;

    GrupaGorskaService(GrupaGorskaRepository repository) {

        this.repositoryGrupaGorska = repository;
    }


    public List<GrupaGorskaEntity> getAllGroups() {

        return repositoryGrupaGorska.findAll();
    }



    public GrupaGorskaEntity getOneGroup(String id) {

        return repositoryGrupaGorska.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }



}
