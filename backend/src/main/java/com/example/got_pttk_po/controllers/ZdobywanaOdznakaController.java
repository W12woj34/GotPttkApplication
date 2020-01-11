package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import com.example.got_pttk_po.services.ZdobywanaOdznakaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/getBadge")
class ZdobywanaOdznakaController {

    private final ZdobywanaOdznakaService service;

    ZdobywanaOdznakaController(ZdobywanaOdznakaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ZdobywanaOdznakaEntity>> allGetBadges() {

        return ResponseEntity.ok(service.getAllGetBadges());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZdobywanaOdznakaEntity> oneGetBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneGetBadge(id));
    }

    @GetMapping(value = "tourist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ZdobywanaOdznakaEntity>> allGetBadgesTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllGetBadgesTourist(id));
    }

    @GetMapping(value = "tourist/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ZdobywanaOdznakaEntity>> allGetBadgesTouristStatus(@PathVariable String id, @PathVariable Integer status) {

        return ResponseEntity.ok(service.getAllGetBadgesTouristStatus(id, status));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZdobywanaOdznakaEntity> newGetBadge(@RequestBody ZdobywanaOdznakaEntity newGetBadge) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ZdobywanaOdznakaEntity> replaceTrip(@RequestBody ZdobywanaOdznakaEntity newTourist, @PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteGetBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.deleteGetBadge(id));
    }

}