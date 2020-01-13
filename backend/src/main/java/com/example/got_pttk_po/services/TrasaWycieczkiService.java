package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.TrasaWycieczkiEntity;
import com.example.got_pttk_po.exceptions.RouteNotFoundException;
import com.example.got_pttk_po.repositories.TrasaWycieczkiRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TrasaWycieczkiService {

    private final TrasaWycieczkiRepository repositoryTrasaWycieczki;

    TrasaWycieczkiService(TrasaWycieczkiRepository repositoryTrasaWycieczki) {
        this.repositoryTrasaWycieczki = repositoryTrasaWycieczki;
    }


    public List<TrasaWycieczkiEntity> getAllTripRoutes() {

        return repositoryTrasaWycieczki.findAll();
    }

    public TrasaWycieczkiEntity getOneTripRoute(Integer id) {

        return repositoryTrasaWycieczki.findById(id)
                .orElseThrow(() -> new RouteNotFoundException(id));
    }

   
}