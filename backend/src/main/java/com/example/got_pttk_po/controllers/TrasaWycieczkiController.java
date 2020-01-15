package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.TrasaWycieczkiAddDTO;
import com.example.got_pttk_po.dto.TrasaWycieczkiUpdateDTO;
import com.example.got_pttk_po.dto.TripRouteReplyDTO;
import com.example.got_pttk_po.services.TrasaWycieczkiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tripRoutes")
class TrasaWycieczkiController {

    private final TrasaWycieczkiService service;

    TrasaWycieczkiController(TrasaWycieczkiService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TripRouteReplyDTO>> allTripRoutes() {

        return ResponseEntity.ok(service.getAllTripRoutes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TripRouteReplyDTO> oneTripRoute(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneTripRoute(id));
    }

    @GetMapping(value = "wycieczka/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TripRouteReplyDTO>> allTripRoutesForTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getAllTripRoutesForTrip(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TripRouteReplyDTO> newTripRoute(@RequestBody TrasaWycieczkiAddDTO newTripRoute) {

        return ResponseEntity.ok(service.addTripRoute(newTripRoute));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TripRouteReplyDTO> replaceTripRoute(@RequestBody TrasaWycieczkiUpdateDTO newTripRoute, @PathVariable Integer id) {

        return ResponseEntity.ok(service.modifyTripRoute(newTripRoute, id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteTripRoute(@PathVariable Integer id) {

        return ResponseEntity.ok(service.deleteTripRoute(id));
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Integer>> deleteTripRoutes(@RequestParam List<Integer> ids) {

        return ResponseEntity.ok(service.deleteTripRoutes(ids));
    }
}