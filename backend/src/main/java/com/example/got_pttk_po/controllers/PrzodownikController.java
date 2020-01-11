package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.entities.PrzodownikEntity;
import com.example.got_pttk_po.entities.UzytkownikEntity;
import com.example.got_pttk_po.services.PrzodownikService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/leaders")
class PrzodownikController {

    private final PrzodownikService service;

    PrzodownikController(PrzodownikService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PrzodownikEntity>> allLeadersId() {

        return ResponseEntity.ok(service.getAllLeaders());
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UzytkownikEntity>> allLeadersAll() {

        return ResponseEntity.ok(service.getAllLeadersAll());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PrzodownikEntity> oneLeaderId(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneLeader(id));
    }

    @GetMapping(value = "/{id}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UzytkownikEntity> oneLeaderAll(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneLeaderAll(id));
    }


    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PrzodownikEntity> newLeader(@RequestBody PrzodownikEntity newLeader) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PrzodownikEntity> replaceLeader(@RequestBody PrzodownikEntity newLeader, @PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteLeader(@PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }
}