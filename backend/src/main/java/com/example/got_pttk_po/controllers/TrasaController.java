package com.example.got_pttk_po.controllers;

import java.util.List;
import com.example.got_pttk_po.dto.RouteReplyDTO;
import com.example.got_pttk_po.services.TrasaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/routes")
class TrasaController {

    private final TrasaService service;

    TrasaController(TrasaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RouteReplyDTO>> allRoutes() {

        return ResponseEntity.ok(service.getAllRoutes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RouteReplyDTO> oneRoute(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneRoute(id));
    }

    @GetMapping(value = "/possible/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RouteReplyDTO>> possibleRoutes(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getPossibleRoutes(id));
    }

    @GetMapping(value = "/subgroup/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RouteReplyDTO>> possibleRoutes(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllRoutesInSubgroup(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RouteReplyDTO> newRoute(@RequestBody RouteReplyDTO newRoute) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RouteReplyDTO> replaceRoute(@RequestBody RouteReplyDTO newRoute, @PathVariable Integer id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

    ResponseEntity<Integer> deleteRoute(@PathVariable Integer id) {

        throw new java.lang.UnsupportedOperationException();
    }
}