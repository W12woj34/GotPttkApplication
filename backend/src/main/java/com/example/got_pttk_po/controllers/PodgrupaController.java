package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.SubgroupReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.PodgrupaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/subgroups")
@Api(tags = "Mountain subgroups info")
class PodgrupaController {

    private final PodgrupaService service;

    PodgrupaController(PodgrupaService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all mountain subgroups")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SubgroupReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<SubgroupReplyDTO>> allSubgroups() {

        return ResponseEntity.ok(service.getAllSubgroups());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific mountain subgroup")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SubgroupReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<SubgroupReplyDTO> oneSubgroup(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneSubgroup(id));
    }

    @GetMapping(value = "/group/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all mountain subgroups in specific group")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SubgroupReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<SubgroupReplyDTO>> allSubgroupsFromGroup(@PathVariable String id) {

        return ResponseEntity.ok(service.getAllSubgroupsFromGroup(id));
    }

}