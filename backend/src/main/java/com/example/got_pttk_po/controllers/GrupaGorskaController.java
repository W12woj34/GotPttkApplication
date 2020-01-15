package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.MountainGroupReplyDTO;
import com.example.got_pttk_po.entities.GrupaGorskaEntity;
import com.example.got_pttk_po.services.GrupaGorskaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/groups")
class GrupaGorskaController {

    private final GrupaGorskaService service;

    GrupaGorskaController(GrupaGorskaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<MountainGroupReplyDTO>> allGroups() {

        return ResponseEntity.ok(service.getAllGroups());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GrupaGorskaEntity> oneGroup(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneGroup(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GrupaGorskaEntity> replaceGroup(@RequestBody GrupaGorskaEntity newGroup, @PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GrupaGorskaEntity> newGroup(@RequestBody GrupaGorskaEntity newGroup) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> deleteGroup(@PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }
}