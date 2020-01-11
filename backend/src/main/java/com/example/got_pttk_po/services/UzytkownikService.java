package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.UzytkownikRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UzytkownikService {

    private final UzytkownikRepository repository;

    UzytkownikService(UzytkownikRepository repository) {
        this.repository = repository;
    }


    public List<UzytkownikEntity> getAllUsers() {

        return repository.findAll();
    }

    public UzytkownikEntity getOneUser(String id) {

        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }



}
