package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.entities.PrzodownikEntity;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.services.PrzodownikService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/leaders")
class PrzodownikController {

    private final PrzodownikService service;

    PrzodownikController(PrzodownikService service) {
        this.service = service;
    }

    @GetMapping("")
    List<PrzodownikEntity> allLeadersId() {
        return service.getAllLeaders();
    }

    @GetMapping("/all")
    List<UzytkownikEntity> allLeadersAll() {
        return service.getAllLeadersAll();
    }

    @PostMapping("")
    PrzodownikEntity newLeader(@RequestBody PrzodownikEntity newTourist) {
        throw new java.lang.UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    PrzodownikEntity oneLeaderId(@PathVariable String id) {
        return service.getOneLeader(id);
    }

    @GetMapping("/{id}/all")
    UzytkownikEntity oneLeaderAll(@PathVariable String id) {
        return service.getOneLeaderAll(id);
    }

    @PutMapping("/{id}")
    PrzodownikEntity replaceLeader(@RequestBody PrzodownikEntity newUser, @PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    void deleteLeader(@PathVariable Long id) {
        throw new java.lang.UnsupportedOperationException();
    }
}