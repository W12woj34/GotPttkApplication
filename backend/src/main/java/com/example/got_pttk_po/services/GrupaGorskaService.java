package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.MountainGroupReplyDTO;
import com.example.got_pttk_po.entities.GrupaGorskaEntity;
import com.example.got_pttk_po.exceptions.GroupNotFoundException;
import com.example.got_pttk_po.repositories.GrupaGorskaRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GrupaGorskaService {

    private final GrupaGorskaRepository repositoryGrupaGorska;

    GrupaGorskaService(GrupaGorskaRepository repository) {

        this.repositoryGrupaGorska = repository;
    }


    public List<MountainGroupReplyDTO> getAllGroups() {

        return repositoryGrupaGorska.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, MountainGroupReplyDTO.class))
                .collect(Collectors.toList());

    }



    public GrupaGorskaEntity getOneGroup(String id) {

        return repositoryGrupaGorska.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id));
    }



}
