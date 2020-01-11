package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.TrasaEntity;
import com.example.got_pttk_po.exceptions.RouteNotFoundException;
import com.example.got_pttk_po.repositories.TrasaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TrasaService {

    private final TrasaRepository repositoryTrasa;

    TrasaService(TrasaRepository repository) {

        this.repositoryTrasa = repository;
    }


    public List<TrasaEntity> getAllRoutes() {

        return repositoryTrasa.findAll();
    }

    public List<TrasaEntity> getPossibleRoutes(Integer id) {

       TrasaEntity route =  repositoryTrasa.findById(id).orElseThrow(() -> new RouteNotFoundException(id));
        return repositoryTrasa.findByPoczatkowy(route.getKoncowy());
    }

    public TrasaEntity getOneRoute(Integer id) {

        return repositoryTrasa.findById(id)
                .orElseThrow(() -> new RouteNotFoundException(id));
    }



}
