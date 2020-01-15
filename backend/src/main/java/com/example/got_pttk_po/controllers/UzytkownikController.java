package com.example.got_pttk_po.controllers;

import java.util.List;
import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.services.UzytkownikService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
class UzytkownikController {

    private final UzytkownikService service;

    UzytkownikController(UzytkownikService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserReplyDTO>> allUsers() {

        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserReplyDTO> oneUser(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneUser(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserReplyDTO> newUser(@RequestBody UserReplyDTO newUser) {

        throw new java.lang.UnsupportedOperationException();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserReplyDTO> replaceUser(@RequestBody UserReplyDTO newUser, @PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> deleteUser(@PathVariable String id) {

        throw new java.lang.UnsupportedOperationException();
    }
}