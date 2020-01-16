package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.RouteReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.TrasaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/routes")
@Api(tags = "Routes info")
class TrasaController {

    private final TrasaService service;

    TrasaController(TrasaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all routes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RouteReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<RouteReplyDTO>> allRoutes() {

        return ResponseEntity.ok(service.getAllRoutes());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RouteReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<RouteReplyDTO> oneRoute(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getOneRoute(id));
    }

    @GetMapping(value = "/possible/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all possible routes from specific route")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RouteReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<RouteReplyDTO>> possibleRoutes(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getPossibleRoutes(id));
    }

    @GetMapping(value = "/subgroup/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all routes in specific mountain subgroup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RouteReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<RouteReplyDTO>> subgroupRoutes(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllRoutesInSubgroup(id));
    }

}