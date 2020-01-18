package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.*;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.ZdobywanaOdznakaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/getBadges")
@Api(tags = "Users badges info and management")
class ZdobywanaOdznakaController {

    private final ZdobywanaOdznakaService service;

    ZdobywanaOdznakaController(ZdobywanaOdznakaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all users badges")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetBadgeReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<GetBadgeReplyDTO>> allGetBadges() {

        return ResponseEntity.ok(service.getAllGetBadges());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific user badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetBadgeReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<GetBadgeReplyDTO> oneGetBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneGetBadge(id));
    }

    @GetMapping(value = "/tourist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all badges for specific tourist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetBadgeReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<GetBadgeReplyDTO>> allGetBadgesTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllGetBadgesTourist(id));
    }

    @GetMapping(value = "/tourist/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all badges with specific status for specific tourist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetBadgeReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<GetBadgeReplyDTO>> allGetBadgesTouristStatus(@PathVariable String id, @PathVariable Integer status) {

        return ResponseEntity.ok(service.getAllGetBadgesTouristStatus(id, status));
    }

    @GetMapping(value = "/tourist/{id}/possible", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all possible badges for specific tourist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BadgeReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<BadgeReplyDTO>> allPossibleBadgesTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllPossibleBadgesTourist(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new user badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetBadgeReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<GetBadgeReplyDTO> newGetBadge(@RequestBody GetBadgeAddDTO dto) {

        return ResponseEntity.ok(service.addGetBadge(dto.getTouristId(), dto.getBadgeId()));
    }

    @PutMapping(value = "/{getBadgeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Change user present badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GetBadgeReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<GetBadgeReplyDTO> replaceGetBadge(@RequestBody GetBadgeUpdateDTO dto, @PathVariable Integer getBadgeId) {

        return ResponseEntity.ok(service.changeBadge(getBadgeId, dto.getNewBadgeId()));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete specific user badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<Integer> deleteGetBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.deleteGetBadge(id));
    }

}