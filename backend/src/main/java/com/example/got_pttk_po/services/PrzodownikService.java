package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.PrzodownikEntity;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.exceptions.LeaderNotFoundException;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.PrzodownikRepository;
import com.example.got_pttk_po.repositories.UzytkownikRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PrzodownikService {

    private final PrzodownikRepository repositoryPrzodownik;
    private final UzytkownikRepository repositoryUzytkownik;

    PrzodownikService(PrzodownikRepository repositoryPrzodownik, UzytkownikRepository repositoryUzytkownik) {
        this.repositoryPrzodownik = repositoryPrzodownik;
        this.repositoryUzytkownik = repositoryUzytkownik;
    }


    public List<PrzodownikEntity> getAllLeaders() {

        return repositoryPrzodownik.findAll();
    }

    public PrzodownikEntity getOneLeader(String id) {

        return repositoryPrzodownik.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UzytkownikEntity> getAllLeadersAll() {

        List<PrzodownikEntity> leadersList = repositoryPrzodownik.findAll();
        List<String> leaderIds = new ArrayList<>();
        for (PrzodownikEntity tourit : leadersList) {
            repositoryUzytkownik.findById(tourit.getNrLicencji()).orElseThrow(() -> new UserNotFoundException(tourit.getNrLicencji()));
            leaderIds.add(tourit.getNrLicencji());
        }
        return repositoryUzytkownik.findAllById(leaderIds);
    }


    public UzytkownikEntity getOneLeaderAll(String id) {

        PrzodownikEntity leader = repositoryPrzodownik.findById(id).orElseThrow(() -> new LeaderNotFoundException(id));
        return repositoryUzytkownik.findById(leader.getNrLicencji()).orElseThrow(() -> new UserNotFoundException(id));
    }

}