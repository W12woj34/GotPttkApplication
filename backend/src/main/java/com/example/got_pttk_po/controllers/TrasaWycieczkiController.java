package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.MountainGroupReplyDTO;
import com.example.got_pttk_po.dto.TripRouteAddDTO;
import com.example.got_pttk_po.dto.TripRouteUpdateDTO;
import com.example.got_pttk_po.dto.TripRouteReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.TrasaWycieczkiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tripRoutes")
@Api(tags = "Trip routes info and management")
class TrasaWycieczkiController {

    private final TrasaWycieczkiService service;

    TrasaWycieczkiController(TrasaWycieczkiService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trip routes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MountainGroupReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripRouteReplyDTO>> allTripRoutes() {

        return ResponseEntity.ok(service.getAllTripRoutes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific trip route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripRouteReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TripRouteReplyDTO> oneTripRoute(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneTripRoute(id));
    }

    @GetMapping(value = "wycieczka/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all trip routes in specific trip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripRouteReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<TripRouteReplyDTO>> allTripRoutesForTrip(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getAllTripRoutesForTrip(id));
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add new trip route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripRouteReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TripRouteReplyDTO> newTripRoute(@RequestBody TripRouteAddDTO dto) {

        return ResponseEntity.ok(service.addTripRoute(dto));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modify existing trip route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = TripRouteReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<TripRouteReplyDTO> replaceTripRoute(@RequestBody TripRouteUpdateDTO dto, @PathVariable Integer id) {

        return ResponseEntity.ok(service.modifyTripRoute(dto, id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete specific trip route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<Integer> deleteTripRoute(@PathVariable Integer id) {

        return ResponseEntity.ok(service.deleteTripRoute(id));
    }

    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete many trip routes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<Integer>> deleteTripRoutes(@RequestParam List<Integer> ids) {

        return ResponseEntity.ok(service.deleteTripRoutes(ids));
    }
}