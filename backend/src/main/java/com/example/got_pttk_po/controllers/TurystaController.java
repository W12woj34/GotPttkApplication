package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.entities.TurystaEntity;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.services.TurystaService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tourists")
class TurystaController {

    private final TurystaService service;

    TurystaController(TurystaService service) {
        this.service = service;
    }

    @GetMapping("")
    List<TurystaEntity> allTouristsId() {
        return service.getAllTourists();
    }

    @GetMapping("/all")
    List<UzytkownikEntity> allTouristsAll() {
        return service.getAllTouristsAll();
    }

    @PostMapping("")
    TurystaEntity newTourist(@RequestBody TurystaEntity newTourist) {
        throw new java.lang.UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    TurystaEntity oneTouristId(@PathVariable String id) {
        return service.getOneTourist(id);
    }

    @GetMapping("/{id}/all")
    UzytkownikEntity oneTouristAll(@PathVariable String id) {
        return service.getOneTouristAll(id);
    }

    @PutMapping("/{id}")
    TurystaEntity replaceTourist(@RequestBody TurystaEntity newTourist, @PathVariable String id) {
        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    void deleteTourist(@PathVariable String id) {
        throw new java.lang.UnsupportedOperationException();
    }
}