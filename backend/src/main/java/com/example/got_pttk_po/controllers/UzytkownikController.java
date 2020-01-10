package com.example.got_pttk_po.controllers;

import java.util.List;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.UzytkownikRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UzytkownikController {

    private final UzytkownikRepository repository;

    UzytkownikController(UzytkownikRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<UzytkownikEntity> all() {
        List<UzytkownikEntity> result =  repository.findAll();
        return result;
    }

    @PostMapping("/users")
    UzytkownikEntity newEmployee(@RequestBody UzytkownikEntity newEmployee) {
        throw new java.lang.UnsupportedOperationException();
    }

    @GetMapping("/user/{id}")
    UzytkownikEntity one(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    UzytkownikEntity replaceEmployee(@RequestBody UzytkownikEntity newEmployee, @PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping("/user/{id}")
    void deleteEmployee(@PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }
}