package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.BadgeReplyDTO;
import com.example.got_pttk_po.services.OdznakaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/badges")
class OdznakaController {

    private final OdznakaService service;

    OdznakaController(OdznakaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BadgeReplyDTO>> allBadges() {

        return ResponseEntity.ok(service.getAllBadges());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BadgeReplyDTO> oneBadge(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneBadge(id));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BadgeReplyDTO> replaceBadge(@RequestBody BadgeReplyDTO newBadge, @PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BadgeReplyDTO> newBadge(@RequestBody BadgeReplyDTO newBadge) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteBadge(@PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }
}