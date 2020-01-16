package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.BadgeReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.OdznakaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/badges")
@Api(tags = "Badges info")
class OdznakaController {

    private final OdznakaService service;

    OdznakaController(OdznakaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all badges")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BadgeReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<BadgeReplyDTO>> allBadges() {

        return ResponseEntity.ok(service.getAllBadges());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BadgeReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<BadgeReplyDTO> oneBadge(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneBadge(id));
    }

}