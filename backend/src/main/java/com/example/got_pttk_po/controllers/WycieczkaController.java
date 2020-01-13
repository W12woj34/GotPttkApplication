package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.entities.WycieczkaEntity;
import com.example.got_pttk_po.services.WycieczkaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/trips")
class WycieczkaController {

    private final WycieczkaService service;

    WycieczkaController(WycieczkaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> allTrips() {

        return ResponseEntity.ok(service.getAllTrips());
    }

    @GetMapping(value = "/tourist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> allTripsTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllTripsTourist());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WycieczkaEntity> oneTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneTrip(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WycieczkaEntity> newTrip(@RequestBody Integer newTripGetBadge) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WycieczkaEntity> replaceTrip(@PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteTrip(@PathVariable Integer id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/all/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Integer>> deleteTrips(@PathVariable Integer id) {

        throw new java.lang.UnsupportedOperationException();
    }
}