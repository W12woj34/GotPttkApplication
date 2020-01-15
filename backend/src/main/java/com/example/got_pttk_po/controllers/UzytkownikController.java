package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.UzytkownikService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
@Api(tags = "Users info")
class UzytkownikController {

    private final UzytkownikService service;

    UzytkownikController(UzytkownikService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<UserReplyDTO>> allUsers() {

        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<UserReplyDTO> oneUser(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneUser(id));
    }

}