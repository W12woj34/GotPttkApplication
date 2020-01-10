package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.entities.PodgrupaEntity;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.exceptions.PodgrupaNotFoundException;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.PodgrupaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PodgrupaController {

    private final PodgrupaRepository repository;

    PodgrupaController(PodgrupaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/podgrupy")
    List<PodgrupaEntity> all() {
        List<PodgrupaEntity> result =  repository.findAll();
        return result;
    }

    @PostMapping("/podgrupy")
    PodgrupaEntity newPodgrupa(@RequestBody PodgrupaEntity newEmployee) {
        throw new java.lang.UnsupportedOperationException();
    }

    @GetMapping("/podgrupa/{id}")
    PodgrupaEntity one(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new PodgrupaNotFoundException(id));
    }

    @PutMapping("/podgrupa/{id}")
    PodgrupaEntity replacePodgrupa(@RequestBody PodgrupaEntity newPodgrupa, @PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping("/podgrupa/{id}")
    void deletePodgrupa(@PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }
}