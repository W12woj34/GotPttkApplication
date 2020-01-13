package com.example.got_pttk_po.controllers;

import java.util.List;
import com.example.got_pttk_po.entities.PodgrupaEntity;
import com.example.got_pttk_po.services.PodgrupaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subgroups")
class PodgrupaController {

    private final PodgrupaService service;

    PodgrupaController(PodgrupaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PodgrupaEntity>> allSubgroups() {

        return ResponseEntity.ok(service.getAllSubgroups());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PodgrupaEntity> oneSubgroup(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneSubgroup(id));
    }

    @GetMapping(value = "/group/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PodgrupaEntity>> allSubgroupsFromGroup(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllSubgroupsFromGroup(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PodgrupaEntity> replaceSubgroup(@RequestBody PodgrupaEntity newSubgroup, @PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PodgrupaEntity> newSubgroup(@RequestBody PodgrupaEntity newSubgroup) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteSubgroup(@PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }
}