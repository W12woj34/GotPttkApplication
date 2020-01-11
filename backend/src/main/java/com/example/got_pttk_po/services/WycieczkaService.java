package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.WycieczkaEntity;
import com.example.got_pttk_po.exceptions.TripNotFoundException;
import com.example.got_pttk_po.repositories.WycieczkaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class WycieczkaService {

    private final WycieczkaRepository repositoryWycieczka;

    WycieczkaService(WycieczkaRepository repository) {
        this.repositoryWycieczka = repository;
    }


    public List<WycieczkaEntity> getAllTrips() {

        return repositoryWycieczka.findAll();
    }


    public List<WycieczkaEntity> getAllTripsTourist() {

        return repositoryWycieczka.findAll();
    }

    public WycieczkaEntity getOneTrip(Integer id) {

        return repositoryWycieczka.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
    }



}
