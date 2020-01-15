package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.TouristReplyDTO;
import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.entities.TurystaEntity;
import com.example.got_pttk_po.exceptions.TouristNotFoundException;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.TurystaRepository;
import com.example.got_pttk_po.repositories.UzytkownikRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TurystaService {

    private final TurystaRepository repositoryTurysta;
    private final UzytkownikRepository repositoryUzytkownik;

    TurystaService(TurystaRepository repositoryTurysta, UzytkownikRepository repositoryUzytkownik) {
        this.repositoryTurysta = repositoryTurysta;
        this.repositoryUzytkownik = repositoryUzytkownik;
    }


    public List<TouristReplyDTO> getAllTourists() {

        return repositoryTurysta.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, TouristReplyDTO.class))
                .collect(Collectors.toList());
    }

    public TouristReplyDTO getOneTourist(String id) {

        return repositoryTurysta.findById(id).map(el -> ModelMapperUtil.map(el, TouristReplyDTO.class))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UserReplyDTO> getAllTouristsAll() {

        List<TurystaEntity> touristsList = repositoryTurysta.findAll();
        List<String> touristIds = new ArrayList<>();
        for (TurystaEntity tourit : touristsList) {
            repositoryUzytkownik.findById(tourit.getNazwa()).orElseThrow(() -> new UserNotFoundException(tourit.getNazwa()));
            touristIds.add(tourit.getNazwa());
        }
        return repositoryUzytkownik.findAllById(touristIds).stream()
                .map(el -> ModelMapperUtil.map(el, UserReplyDTO.class))
                .collect(Collectors.toList());
    }


    public UserReplyDTO getOneTouristAll(String id) {

        TurystaEntity tourist = repositoryTurysta.findById(id).orElseThrow(() -> new TouristNotFoundException(id));
        return repositoryUzytkownik.findById(tourist.getNazwa())
                .map(el -> ModelMapperUtil.map(el, UserReplyDTO.class)).orElseThrow(() -> new UserNotFoundException(id));
    }

}