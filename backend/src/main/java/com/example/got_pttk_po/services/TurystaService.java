package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.TurystaEntity;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.exceptions.TouristNotFoundException;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.TurystaRepository;
import com.example.got_pttk_po.repositories.UzytkownikRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TurystaService {

    private final TurystaRepository repositoryTurysta;
    private final UzytkownikRepository repositoryUzytkownik;

    TurystaService(TurystaRepository repositoryTurysta, UzytkownikRepository repositoryUzytkownik) {
        this.repositoryTurysta = repositoryTurysta;
        this.repositoryUzytkownik = repositoryUzytkownik;
    }


    public List<TurystaEntity> getAllTourists() {

        return repositoryTurysta.findAll();
    }

    public TurystaEntity getOneTourist(String id) {

        return repositoryTurysta.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UzytkownikEntity> getAllTouristsAll() {

        List<TurystaEntity> touristsList = repositoryTurysta.findAll();
        List<String> touristIds = new ArrayList<>();
        for (TurystaEntity tourit : touristsList) {
            repositoryUzytkownik.findById(tourit.getNazwa()).orElseThrow(() -> new UserNotFoundException(tourit.getNazwa()));
            touristIds.add(tourit.getNazwa());
        }
        return repositoryUzytkownik.findAllById(touristIds);
    }


    public UzytkownikEntity getOneTouristAll(String id) {

        TurystaEntity tourist = repositoryTurysta.findById(id).orElseThrow(() -> new TouristNotFoundException(id));
        return repositoryUzytkownik.findById(tourist.getNazwa()).orElseThrow(() -> new UserNotFoundException(id));
    }

}