package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import com.example.got_pttk_po.exceptions.GetBadgeNotFoundException;
import com.example.got_pttk_po.repositories.ZdobywanaOdznakaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ZdobywanaOdznakaService {

    private final ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;

    ZdobywanaOdznakaService(ZdobywanaOdznakaRepository repository) {
        this.repositoryZdobywanaOdznaka = repository;
    }


    public List<ZdobywanaOdznakaEntity> getAllGetBadges() {
        return repositoryZdobywanaOdznaka.findAll();
    }

    public List<ZdobywanaOdznakaEntity> getAllGetBadgesTourist(String id) {
        return repositoryZdobywanaOdznaka.findByTurysta(id);
    }

    public List<ZdobywanaOdznakaEntity> getAllGetBadgesTouristStatus(String id, Integer status) {
        return repositoryZdobywanaOdznaka.findByTurystaAndStatus(id, status);
    }

    public ZdobywanaOdznakaEntity getOneGetBadge(Integer id) {
        return repositoryZdobywanaOdznaka.findById(id)
                .orElseThrow(() -> new GetBadgeNotFoundException(id));
    }

    public Integer deleteGetBadge(Integer id){
        repositoryZdobywanaOdznaka.findById(id).orElseThrow(() -> new GetBadgeNotFoundException(id));
        repositoryZdobywanaOdznaka.deleteById(id);
        return id;
    }


}
