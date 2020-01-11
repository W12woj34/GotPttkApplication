package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.OdznakaEntity;
import com.example.got_pttk_po.exceptions.BadgeNotFoundException;
import com.example.got_pttk_po.repositories.OdznakaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OdznakaService {

    private final OdznakaRepository repositoryOdznaka;

    OdznakaService(OdznakaRepository repository) {
        this.repositoryOdznaka = repository;
    }


    public List<OdznakaEntity> getAllBadges() {
        return repositoryOdznaka.findAll();
    }

    public OdznakaEntity getOneBadge(String id) {
        return repositoryOdznaka.findById(id)
                .orElseThrow(() -> new BadgeNotFoundException(id));
    }



}
