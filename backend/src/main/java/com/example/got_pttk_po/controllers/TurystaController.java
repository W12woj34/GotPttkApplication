package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.TouristReplyDTO;
import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.TurystaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tourists")
@Api(tags = "Tourists info")
class TurystaController {

    private final TurystaService service;

    TurystaController(TurystaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all tourists ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TouristReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TouristReplyDTO>> allTouristsId() {
        return ResponseEntity.ok(service.getAllTourists());
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all tourists")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<UserReplyDTO>> allTouristsAll() {

        return ResponseEntity.ok(service.getAllTouristsAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific tourist id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TouristReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TouristReplyDTO> oneTouristId(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneTourist(id));
    }

    @GetMapping(value = "/{id}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific tourist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<UserReplyDTO> oneTouristAll(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneTouristAll(id));
    }

}