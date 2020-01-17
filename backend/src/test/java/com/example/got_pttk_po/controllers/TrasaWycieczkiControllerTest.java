package com.example.got_pttk_po.controllers;

import com.example.got_pttk_po.dto.TripRouteReplyDTO;
import com.example.got_pttk_po.exceptions.TripRouteNotFoundException;
import com.example.got_pttk_po.services.TrasaWycieczkiService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrasaWycieczkiController.class)
public class TrasaWycieczkiControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private TrasaWycieczkiService service;
    private TripRouteReplyDTO tripRoute;

    @Before
    public void setUp() {
        Date date = new Date(1234321);
        tripRoute = new TripRouteReplyDTO(1, false, 3, date, 2, 7);
    }

    @Test
    public void shouldReturnListOfTripRoutes() throws Exception {
        List<TripRouteReplyDTO> allTripRoutes = Collections.singletonList(tripRoute);
        given(service.getAllTripRoutes()).willReturn(allTripRoutes);

        mvc.perform(get("/tripRoutes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].numer", Matchers.is(tripRoute.getNumer())));
    }

    @Test
    public void shouldReturnEmptyListIfNoTripRoutes() throws Exception {
        List<TripRouteReplyDTO> tripRoutes = new ArrayList<>();
        given(service.getAllTripRoutes()).willReturn(tripRoutes);

        mvc.perform(get("/tripRoutes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(jsonPath("$", Matchers.is(tripRoutes)));
    }

    @Test
    public void shouldReturnTripRouteWithId() throws Exception {
        Integer id = 1;
        given(service.getOneTripRoute(id)).willReturn(tripRoute);

        mvc.perform(get("/tripRoutes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numer").value(id));
    }

    @Test
    public void shouldThrowExceptionIfWrongId() throws Exception {
        Integer id = 4;
        given(service.getOneTripRoute(id)).willThrow(new TripRouteNotFoundException(id));

        mvc.perform(get("/tripRoutes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Could not find trip route 4")));
    }


    @Test
    public void shouldReturnTripRouteIdIfDeleted() throws Exception {
        Integer id = 1;
        given(service.deleteTripRoute(id)).willReturn(id);
        mvc.perform(delete("/tripRoutes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowExceptionIdWrongDeleteId() throws Exception {
        Integer id = 4;
        given(service.deleteTripRoute(id)).willThrow(new TripRouteNotFoundException(id));
        mvc.perform(delete("/tripRoutes/{id}", id))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Could not find trip route 4")));
    }

    @Test
    public void shouldReturnListOfIdsIfDelete() throws Exception {
        List<Integer> tripRoutesIds = new ArrayList<>();
        tripRoutesIds.add(1);
        tripRoutesIds.add(2);
        given(service.deleteTripRoutes(tripRoutesIds)).willReturn(tripRoutesIds);
        mvc.perform(delete("/tripRoutes?ids=1,2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]", is(tripRoutesIds)));
    }

    @Test
    public void shouldThrowExceptionIdsWrongDeleteIds() throws Exception {
        List<Integer> listIds = new ArrayList<>();
        listIds.add(1);
        listIds.add(7);
        given(service.deleteTripRoutes(listIds)).willThrow(new TripRouteNotFoundException(7));
        mvc.perform(delete("/tripRoutes?ids=1,7")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Could not find trip route 7")));
    }

}
