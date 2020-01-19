package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.TripRouteReplyDTO;
import com.example.got_pttk_po.dto.TripRouteUpdateDTO;
import com.example.got_pttk_po.entities.TrasaWycieczkiEntity;
import com.example.got_pttk_po.entities.WycieczkaEntity;
import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import com.example.got_pttk_po.exceptions.TripRouteInvalidException;
import com.example.got_pttk_po.exceptions.TripRouteNotFoundException;
import com.example.got_pttk_po.repositories.TrasaWycieczkiRepository;
import com.example.got_pttk_po.repositories.WycieczkaRepository;
import com.example.got_pttk_po.repositories.ZdobywanaOdznakaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrasaWycieczkiServiceTest {
    private TrasaWycieczkiEntity tripRoute;

    @Mock
    TrasaWycieczkiRepository repositoryTrasaWycieczki;
    @Mock
    WycieczkaRepository repositoryWycieczka;
    @Mock
    ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;

    @InjectMocks
    TrasaWycieczkiService service;

    @Before
    public void setUp() {
        Date date = new Date(1234321);
        tripRoute = new TrasaWycieczkiEntity();
        tripRoute.setNumer(1);
        tripRoute.setPowtozona(false);
        tripRoute.setIndeks(1);
        tripRoute.setData(date);
        tripRoute.setWycieczka(1);
        tripRoute.setTrasa(7);
    }


    @Test
    public void shouldReturnTripRouteIdIfDelete() {
        ZdobywanaOdznakaEntity badge1 = new ZdobywanaOdznakaEntity();
        badge1.setId(1);
        WycieczkaEntity trip1 = new WycieczkaEntity();
        trip1.setNumer(1);
        trip1.setOdznaka(1);
        TrasaWycieczkiEntity tripRoute1 = new TrasaWycieczkiEntity();
        tripRoute1.setNumer(1);
        tripRoute1.setWycieczka(1);
        tripRoute1.setIndeks(1);
        List<TrasaWycieczkiEntity> routeList = new ArrayList<>();
        routeList.add(tripRoute1);
        when(repositoryTrasaWycieczki.findById(1)).thenReturn(Optional.of(tripRoute1));
        when(repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(tripRoute.getWycieczka())).thenReturn(routeList);
        when(repositoryWycieczka.findById(tripRoute.getWycieczka())).thenReturn(Optional.of(trip1));
        when(repositoryZdobywanaOdznaka.findById(trip1.getOdznaka())).thenReturn(Optional.of(badge1));

        Integer deletedId = service.deleteTripRoute(tripRoute1.getNumer());

        assertThat(deletedId).isEqualTo(tripRoute1.getNumer());
    }

    @Test
    public void shouldThrowExceptionIfWrongIdToDelete() {

        when(repositoryTrasaWycieczki.findById(1)).thenThrow(new TripRouteNotFoundException(tripRoute.getNumer()));

        TripRouteNotFoundException ex = assertThrows(TripRouteNotFoundException.class, () ->
                service.deleteTripRoute(tripRoute.getNumer()));

        assertThat(ex.getMessage()).isEqualTo("Could not find trip route 1");
    }

    @Test
    public void shouldThrowExceptionIfWrongIdsToDelete() {

        List<Integer> idsToDelete = new ArrayList<>();
        idsToDelete.add(1);
        idsToDelete.add(2);
        ArrayList<TrasaWycieczkiEntity> foundInDatabase = new ArrayList<>();
        foundInDatabase.add(tripRoute);
        when(repositoryTrasaWycieczki.findByNumerIn(idsToDelete)).thenReturn(foundInDatabase);

        TripRouteInvalidException ex = assertThrows(TripRouteInvalidException.class, () ->
                service.deleteTripRoutes(idsToDelete));

        assertThat(ex.getMessage()).isEqualTo("Could not add, modify or delete trip route -1");
    }


    @Test
    public void shouldChangeTripRouteDate() {
        List<TrasaWycieczkiEntity> list = new ArrayList<>();
        TrasaWycieczkiEntity tripRoute2 = new TrasaWycieczkiEntity();
        Date date2 = new Date(1234323);
        tripRoute2.setNumer(1);
        tripRoute2.setPowtozona(false);
        tripRoute2.setIndeks(1);
        tripRoute2.setData(date2);
        tripRoute2.setWycieczka(1);
        tripRoute2.setTrasa(8);
        list.add(tripRoute);
        list.add(tripRoute2);
        Date newDate = new Date(1234322);
        when(repositoryTrasaWycieczki.findById(tripRoute.getNumer())).thenReturn(Optional.of(tripRoute));
        when(repositoryTrasaWycieczki
                .findByWycieczkaOrderByDataDesc(tripRoute.getWycieczka())).thenReturn(list);
        TripRouteUpdateDTO data = new TripRouteUpdateDTO(newDate);
        TripRouteReplyDTO modifyTripRoute = service.modifyTripRoute(data, tripRoute.getNumer());

        assertThat(modifyTripRoute.getData()).isEqualTo(newDate);
    }

    @Test
    public void shouldThrowExceptionIfWrongDate() {

        List<TrasaWycieczkiEntity> list = new ArrayList<>();
        TrasaWycieczkiEntity tripRoute2 = new TrasaWycieczkiEntity();
        Date date2 = new Date(1234323);
        tripRoute2.setNumer(1);
        tripRoute2.setPowtozona(false);
        tripRoute2.setIndeks(1);
        tripRoute2.setData(date2);
        tripRoute2.setWycieczka(1);
        tripRoute2.setTrasa(8);
        list.add(tripRoute);
        list.add(tripRoute2);
        Date newDate = new Date(1234324);
        when(repositoryTrasaWycieczki.findById(tripRoute.getNumer())).thenReturn(Optional.of(tripRoute));
        when(repositoryTrasaWycieczki
                .findByWycieczkaOrderByDataDesc(tripRoute.getWycieczka())).thenReturn(list);
        TripRouteUpdateDTO data = new TripRouteUpdateDTO(newDate);

        TripRouteInvalidException ex = assertThrows(TripRouteInvalidException.class, () ->
                service.modifyTripRoute(data, tripRoute.getNumer()));

        assertThat(ex.getMessage()).isEqualTo("Could not add, modify or delete trip route 1");
    }
}