package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.*;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.WycieczkaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/trips")
@Api(tags = "Trips info and management")
class WycieczkaController {

    private final WycieczkaService service;

    WycieczkaController(WycieczkaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trips")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripReplyDTO>> allTrips() {

        return ResponseEntity.ok(service.getAllTrips());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific trip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TripReplyDTO> oneTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneTrip(id));
    }

    @GetMapping(value = "/leader/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trips verified by specific leader")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripReplyDTO>> allTripsLeader(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllTripsLeader(id));
    }

    @GetMapping(value = "/leader/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trips with specific status, verified by specific leader")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripReplyDTO>> allTripsLeaderStatus(@PathVariable String id, @PathVariable Integer status) {

        return ResponseEntity.ok(service.getAllTripsLeaderStatus(id, status));
    }


    @GetMapping(value = "/tourist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trips for specific tourist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripReplyDTO>> allTripsTourist(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllTripsTourist(id));
    }

    @GetMapping(value = "/tourist/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trips with specific status for specific tourist")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripReplyDTO>> allTripsTouristStatus(@PathVariable String id, @PathVariable Integer status) {

        return ResponseEntity.ok(service.getAllTripsTouristStatus(id, status));
    }

    @GetMapping(value = "/getBadge/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trips for specific user badge")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripReplyDTO>> allTripBadge(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getAllTripBadge(id));
    }

    @GetMapping(value = "/points/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Number of points for specific trip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<Integer> points(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getPoints(id));
    }

    @GetMapping(value = "/groups/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of group for specific trip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<String>> groups(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getGroups(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new trip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TripReplyDTO> newTrip(@RequestBody TripAddDTO dto) {

        return ResponseEntity.ok(service.addTrip(dto.getNewTripGetBadge()));
    }

    @PutMapping(value = "/verify/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Send specific trip to verification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TripReplyDTO> verifyTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.sendTripToVerification(id));
    }

    @PutMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Send many trips to verification")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripReplyDTO>> verifyTrips(@RequestBody TripVerifyDTO dto) {

        return ResponseEntity.ok(service.sendTripsToVerification(dto.getIds()));
    }

    @PutMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Change specific trip status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TripReplyDTO> changeTripStatus(@RequestBody TripStatusUpdateDTO dto, @PathVariable Integer id) {

        return ResponseEntity.ok(service.changeTripStatus(id, dto.getStatus()));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete specific trip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<Integer> deleteTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.deleteTrip(id));
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete many trips")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<Integer>> deleteTrips(@RequestParam List<Integer> ids) {

        return ResponseEntity.ok(service.deleteTrips(ids));
    }
}