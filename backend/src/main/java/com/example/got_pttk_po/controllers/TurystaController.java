package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.TouristReplyDTO;
import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.services.TurystaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tourists")
class TurystaController {

    private final TurystaService service;

    TurystaController(TurystaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TouristReplyDTO>> allTouristsId() {
        return ResponseEntity.ok(service.getAllTourists());
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserReplyDTO>> allTouristsAll() {

        return ResponseEntity.ok(service.getAllTouristsAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TouristReplyDTO> oneTouristId(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneTourist(id));
    }

    @GetMapping(value = "/{id}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserReplyDTO> oneTouristAll(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneTouristAll(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TouristReplyDTO> newTourist(@RequestBody TouristReplyDTO newTourist) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TouristReplyDTO> replaceTourist(@RequestBody TouristReplyDTO newTourist, @PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteTourist(@PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }
}