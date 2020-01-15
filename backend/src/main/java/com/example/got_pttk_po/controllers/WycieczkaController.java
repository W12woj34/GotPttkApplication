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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WycieczkaEntity> oneTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneTrip(id));
    }

    @GetMapping(value = "/leader/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> allTripsLeader(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllTripsLeader(id));
    }

    @GetMapping(value = "/leader/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> allTripsLeaderStatus(@PathVariable String id, @PathVariable Integer status) {

        return ResponseEntity.ok(service.getAllTripsLeaderStatus(id, status));
    }


    @GetMapping(value = "/tourist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> allTripsTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllTripsTourist(id));
    }

    @GetMapping(value = "/tourist/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> allTripsTouristStatus(@PathVariable String id, @PathVariable Integer status) {

        return ResponseEntity.ok(service.getAllTripsTouristStatus(id, status));
    }

    @GetMapping(value = "/getBadge/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> allTripBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getAllTripBadge(id));
    }

    @GetMapping(value = "/points/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> points(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getPoints(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WycieczkaEntity> newTrip(@RequestBody Integer newTripGetBadge) {

        return ResponseEntity.ok(service.addTrip(newTripGetBadge));
    }

    @PutMapping(value = "/verify/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WycieczkaEntity> verifyTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.sendTripToVerification(id));
    }

    @PutMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WycieczkaEntity>> verifyTrip(@RequestBody List<Integer> ids) {

        return ResponseEntity.ok(service.sendTripsToVerification(ids));
    }

    @PutMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WycieczkaEntity> changeTripStatus(@RequestBody Integer status, @PathVariable Integer id) {

        return ResponseEntity.ok(service.changeTripStatus(id, status));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.deleteTrip(id));
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Integer>> deleteTrips(@RequestParam List<Integer> ids) {

        return ResponseEntity.ok(service.deleteTrips(ids));
    }
}