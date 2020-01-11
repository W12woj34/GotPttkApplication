package com.example.got_pttk_po.controllers;

import java.util.List;
import com.example.got_pttk_po.entities.OdznakaEntity;
import com.example.got_pttk_po.entities.TrasaEntity;
import com.example.got_pttk_po.services.OdznakaService;
import com.example.got_pttk_po.services.TrasaService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/routes")
class TrasaController {

    private final TrasaService service;

    TrasaController(TrasaService service) {
        this.service = service;
    }

    @GetMapping("")
    List<TrasaEntity> allRoutes() {
        return service.getAllRoutes();
    }

    @PostMapping("")
    TrasaEntity newRoute(@RequestBody TrasaEntity newRoute) {
        throw new java.lang.UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    TrasaEntity oneRoute(@PathVariable Integer id) {
        return service.getOneRoute(id);
    }

    @GetMapping("/possible/{id}")
    List<TrasaEntity> possibleRoutes(@PathVariable Integer id) {
        return service.getPossibleRoutes(id);
    }

    @PutMapping("/{id}")
    TrasaEntity replaceRoute(@RequestBody TrasaEntity newRoute, @PathVariable Integer id) {
        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    void deleteRoute(@PathVariable Integer id) {
        throw new java.lang.UnsupportedOperationException();
    }
}