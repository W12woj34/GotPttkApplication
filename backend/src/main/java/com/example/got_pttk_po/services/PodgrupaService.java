package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.PodgrupaEntity;
import com.example.got_pttk_po.exceptions.SubgroupNotFoundException;
import com.example.got_pttk_po.repositories.PodgrupaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PodgrupaService {

    private final PodgrupaRepository repositoryPodgrupa;

    PodgrupaService(PodgrupaRepository repository) {

        this.repositoryPodgrupa = repository;
    }


    public List<PodgrupaEntity> getAllSubgroups() {

        return repositoryPodgrupa.findAll();
    }

    public List<PodgrupaEntity> getAllSubgroupsFromGroup(String id) {

        return repositoryPodgrupa.findByGrupa(id);
    }

    public PodgrupaEntity getOneSubgroup(String id) {

        return repositoryPodgrupa.findById(id)
                .orElseThrow(() -> new SubgroupNotFoundException(id));
    }



}
