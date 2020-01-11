package com.example.got_pttk_po.controllers;

import java.util.List;
import com.example.got_pttk_po.entities.OdznakaEntity;
import com.example.got_pttk_po.services.OdznakaService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/badges")
class OdznakaController {

    private final OdznakaService service;

    OdznakaController(OdznakaService service) {
        this.service = service;
    }

    @GetMapping("")
    List<OdznakaEntity> allBadges() {
        return service.getAllBadges();
    }

    @PostMapping("")
    OdznakaEntity newBadge(@RequestBody OdznakaEntity newBadge) {
        throw new java.lang.UnsupportedOperationException();
    }

    @GetMapping("/{id}")
    OdznakaEntity oneBadge(@PathVariable String id) {
        return service.getOneBadge(id);
    }

    @PutMapping("/{id}")
    OdznakaEntity replaceBadge(@RequestBody OdznakaEntity newBadge, @PathVariable String id) {
        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    void deleteBadge(@PathVariable String id) {
        throw new java.lang.UnsupportedOperationException();
    }
}