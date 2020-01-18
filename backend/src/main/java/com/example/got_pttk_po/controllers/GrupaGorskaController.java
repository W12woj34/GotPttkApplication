package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.MountainGroupReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.GrupaGorskaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/groups")
@Api(tags = "Mountain groups info")
class GrupaGorskaController {

    private final GrupaGorskaService service;

    GrupaGorskaController(GrupaGorskaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all mountain groups")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MountainGroupReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<MountainGroupReplyDTO>> allGroups() {

        return ResponseEntity.ok(service.getAllGroups());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific mountain group")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MountainGroupReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<MountainGroupReplyDTO> oneGroup(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneGroup(id));
    }

    @GetMapping(value = "/trip/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of groups for specific trip")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MountainGroupReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<MountainGroupReplyDTO>> groups(@PathVariable Integer id) {

        return ResponseEntity.ok(service.getGroups(id));
    }

}