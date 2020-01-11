package com.example.got_pttk_po.controllers;

import java.util.List;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.services.UzytkownikService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
class UzytkownikController {

    private final UzytkownikService service;

    UzytkownikController(UzytkownikService service) {
        this.service = service;
    }

    @GetMapping("")
    List<UzytkownikEntity> allUsers() {
        return service.getAllUsers();
    }

    @PostMapping("")
    UzytkownikEntity newUser(@RequestBody UzytkownikEntity newUser) {
        throw new java.lang.UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    UzytkownikEntity oneUser(@PathVariable String id) {
        return service.getOneUser(id);
    }

    @PutMapping("/{id}")
    UzytkownikEntity replaceUser(@RequestBody UzytkownikEntity newUser, @PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }
}