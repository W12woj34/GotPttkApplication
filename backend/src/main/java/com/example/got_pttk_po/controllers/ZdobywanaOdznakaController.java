package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.BadgeReplyDTO;
import com.example.got_pttk_po.dto.NewGetBadgeDTO;
import com.example.got_pttk_po.dto.GetBadgeReplyDTO;
import com.example.got_pttk_po.services.ZdobywanaOdznakaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/getBadges")
class ZdobywanaOdznakaController {

    private final ZdobywanaOdznakaService service;

    ZdobywanaOdznakaController(ZdobywanaOdznakaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GetBadgeReplyDTO>> allGetBadges() {

        return ResponseEntity.ok(service.getAllGetBadges());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetBadgeReplyDTO> oneGetBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneGetBadge(id));
    }

    @GetMapping(value = "/tourist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GetBadgeReplyDTO>> allGetBadgesTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllGetBadgesTourist(id));
    }

    @GetMapping(value = "/tourist/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<GetBadgeReplyDTO>> allGetBadgesTouristStatus(@PathVariable String id, @PathVariable Integer status) {

        return ResponseEntity.ok(service.getAllGetBadgesTouristStatus(id, status));
    }

    @GetMapping(value = "/tourist/{id}/possible", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<BadgeReplyDTO>> allPossibleBadgesTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllPossibleBadgesTourist(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetBadgeReplyDTO> newGetBadge(@RequestBody NewGetBadgeDTO newGetBadge) {

        return ResponseEntity.ok(service.addGetBadge(newGetBadge.getTouristId(), newGetBadge.getBadgeId()));
    }

    @PutMapping(value = "/{getBadgeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetBadgeReplyDTO> replaceTrip(@RequestBody String newBadgeId, @PathVariable Integer getBadgeId) {

        return ResponseEntity.ok(service.changeBadge(getBadgeId, newBadgeId));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteGetBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.deleteGetBadge(id));
    }

}