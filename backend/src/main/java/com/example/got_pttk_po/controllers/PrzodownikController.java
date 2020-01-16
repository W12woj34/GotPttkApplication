package com.example.got_pttk_po.controllers;

import java.util.List;

import com.example.got_pttk_po.dto.LeaderReplyDTO;
import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.exceptionHandlers.AppExceptionDTO;
import com.example.got_pttk_po.services.PrzodownikService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/leaders")
@Api(tags = "Leaders info")
class PrzodownikController {

    private final PrzodownikService service;

    PrzodownikController(PrzodownikService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all leaders ids")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LeaderReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<LeaderReplyDTO>> allLeadersId() {

        return ResponseEntity.ok(service.getAllLeaders());
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List of all leaders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserReplyDTO.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<List<UserReplyDTO>> allLeadersAll() {

        return ResponseEntity.ok(service.getAllLeadersAll());
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific leader id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = LeaderReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<LeaderReplyDTO> oneLeaderId(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneLeader(id));
    }

    @GetMapping(value = "/{id}/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Specific leader")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserReplyDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = AppExceptionDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = AppExceptionDTO.class)})
    ResponseEntity<UserReplyDTO> oneLeaderAll(@PathVariable String id) {

        return ResponseEntity.ok(service.getOneLeaderAll(id));
    }

}