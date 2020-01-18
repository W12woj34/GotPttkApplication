package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.TripReplyDTO;
import com.example.got_pttk_po.entities.*;
import com.example.got_pttk_po.exceptions.SubgroupNotFoundException;
import com.example.got_pttk_po.exceptions.TripNotFoundException;
import com.example.got_pttk_po.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WycieczkaServiceTest {
    private WycieczkaEntity trip1;
    private WycieczkaEntity trip3;
    private ZdobywanaOdznakaEntity badge;
    @Mock
    TrasaWycieczkiRepository repositoryTrasaWycieczki;
    @Mock
    WycieczkaRepository repositoryWycieczka;
    @Mock
    ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;
    @Mock
    TrasaRepository repositoryTrasa;
    @Mock
    PodgrupaRepository repositoryPodgrupa;
    @Mock
    UprawnieniaPrzodownikaRepository repositoryUprawnieniaPrzodownika;

    @InjectMocks
    WycieczkaService service;

    @Before
    public void setUp() {
        TurystaEntity tourist = new TurystaEntity();
        tourist.setNazwa("0");
        badge = new ZdobywanaOdznakaEntity();
        badge.setId(0);
        badge.setTurysta("0");
        trip1 = new WycieczkaEntity();
        trip1.setNumer(0);
        trip1.setStatus(0);
        trip1.setOdznaka(0);
        trip1.setPrzodownik("2");
        WycieczkaEntity trip2 = new WycieczkaEntity();
        trip2.setNumer(1);
        trip2.setStatus(1);
        trip2.setOdznaka(0);
        trip3 = new WycieczkaEntity();
        trip3.setNumer(2);
        trip3.setStatus(0);
        trip3.setOdznaka(0);
    }

    @Test
    public void shouldReturnTripsLeaderStatus() {

        List<WycieczkaEntity> trips = new ArrayList<>();
        trips.add(trip1);
        when(repositoryWycieczka.findByPrzodownikAndStatus(trip1.getPrzodownik(), 0)).thenReturn(trips);

        List<TripReplyDTO> getTrips = service.getAllTripsLeaderStatus(trip1.getPrzodownik(), 0);

        assertThat(getTrips.size()).isEqualTo(1);
        assertThat(getTrips.get(0).getStatus()).isEqualTo(0);
        assertThat(getTrips.get(0).getOdznaka()).isEqualTo(0);
        assertThat(getTrips.get(0).getPrzodownik()).isEqualTo("2");
    }

    @Test
    public void shouldReturnEmptyListIfWrongLeader() {

        when(repositoryWycieczka.findByPrzodownikAndStatus("3", 0)).thenReturn(new ArrayList<>());

        List<TripReplyDTO> getTrips = service.getAllTripsLeaderStatus("3", 0);

        assertThat(getTrips.size()).isEqualTo(0);

    }

    @Test
    public void shouldReturnTripsTouristStatus() {
        List<ZdobywanaOdznakaEntity> badges = new ArrayList<>();
        badges.add(badge);
        List<Integer> badgesIds = new ArrayList<>();
        badgesIds.add(badge.getId());
        List<WycieczkaEntity> trips = new ArrayList<>();
        trips.add(trip1);
        trips.add(trip3);
        when(repositoryWycieczka.findByOdznakaInAndStatus(badgesIds, trip1.getStatus())).thenReturn(trips);
        when(repositoryZdobywanaOdznaka.findByTurysta(badge.getTurysta())).thenReturn(badges);

        List<TripReplyDTO> getTrips = service.getAllTripsTouristStatus(badge.getTurysta(), trip1.getStatus());

        assertThat(getTrips.size()).isEqualTo(2);
        assertThat(getTrips.get(0).getStatus()).isEqualTo(0);
        assertThat(getTrips.get(0).getOdznaka()).isEqualTo(0);
        assertThat(getTrips.get(0).getPrzodownik()).isEqualTo("2");
        assertThat(getTrips.get(0).getNumer()).isEqualTo(0);
        assertThat(getTrips.get(1).getStatus()).isEqualTo(0);
        assertThat(getTrips.get(1).getOdznaka()).isEqualTo(0);
        assertThat(getTrips.get(1).getPrzodownik()).isEqualTo(null);
        assertThat(getTrips.get(1).getNumer()).isEqualTo(2);
    }

    @Test
    public void shouldReturnEmptyListIfWrongStatus() {

        List<ZdobywanaOdznakaEntity> badges = new ArrayList<>();
        badges.add(badge);
        List<Integer> badgesIds = new ArrayList<>();
        badgesIds.add(badge.getId());
        when(repositoryWycieczka.findByOdznakaInAndStatus(badgesIds, 5)).thenReturn(new ArrayList<>());
        when(repositoryZdobywanaOdznaka.findByTurysta(badge.getTurysta())).thenReturn(badges);
        List<TripReplyDTO> getTrips = service.getAllTripsTouristStatus(badge.getTurysta(), 5);

        assertThat(getTrips.size()).isEqualTo(0);


    }

    @Test
    public void shouldReturnSentTripData() {
        UprawnieniaPrzodownikaEntity permission = new UprawnieniaPrzodownikaEntity();
        List<UprawnieniaPrzodownikaEntity> permissions = new ArrayList<>();
        permissions.add(permission);
        permission.setGrupa("1");
        permission.setPrzodownik("2");
        PodgrupaEntity subgroup = new PodgrupaEntity();
        subgroup.setId("1");
        subgroup.setGrupa("1");
        TrasaWycieczkiEntity tripRoute1 = new TrasaWycieczkiEntity();
        tripRoute1.setWycieczka(0);
        tripRoute1.setTrasa(0);
        TrasaWycieczkiEntity tripRoute2 = new TrasaWycieczkiEntity();
        tripRoute2.setWycieczka(0);
        tripRoute2.setTrasa(1);
        TrasaEntity route1 = new TrasaEntity();
        route1.setNumer(0);
        route1.setPodgrupa("1");
        TrasaEntity route2 = new TrasaEntity();
        route2.setPodgrupa("1");
        route2.setNumer(1);
        List<TrasaWycieczkiEntity> tripRoutes = new ArrayList<>();
        tripRoutes.add(tripRoute1);
        tripRoutes.add(tripRoute2);
        List<String> subgroupsIds= new ArrayList<>();
        subgroupsIds.add(subgroup.getGrupa());

        when(repositoryWycieczka.findById(trip1.getNumer())).thenReturn(Optional.of(trip1));
        when(repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(trip1.getNumer())).thenReturn(tripRoutes);
        when(repositoryTrasa.findById(tripRoute1.getNumer())).thenReturn(Optional.of(route1));
        when(repositoryTrasa.findById(tripRoute2.getNumer())).thenReturn(Optional.of(route2));
        when(repositoryPodgrupa.findById(route1.getPodgrupa())).thenReturn(Optional.of(subgroup));
        when(repositoryUprawnieniaPrzodownika.findByGrupaIn(subgroupsIds)).thenReturn(permissions);

        TripReplyDTO tripData = service.sendTripToVerification(trip1.getNumer());

        assertThat(tripData.getStatus()).isEqualTo(3);
        assertThat(tripData.getPrzodownik()).isEqualTo("2");
        assertThat(tripData.getOdznaka()).isEqualTo(0);
        assertThat(tripData.getNumer()).isEqualTo(0);
    }

    @Test
    public void shouldThrowExceptionIfWrongTrip() {



        when(repositoryWycieczka.findById(5)).thenThrow(new TripNotFoundException(5));

        TripNotFoundException ex = assertThrows(TripNotFoundException.class, () ->
                service.sendTripToVerification(5));

        assertThat(ex.getMessage()).isEqualTo("Could not find trip 5");
    }

    @Test
    public void shouldThrowExceptionIfSubgroupNotExist() {
        UprawnieniaPrzodownikaEntity permission = new UprawnieniaPrzodownikaEntity();
        permission.setGrupa("1");
        permission.setPrzodownik("2");
        PodgrupaEntity subgroup = new PodgrupaEntity();
        subgroup.setId("1");
        subgroup.setGrupa("1");
        TrasaWycieczkiEntity tripRoute1 = new TrasaWycieczkiEntity();
        tripRoute1.setWycieczka(0);
        tripRoute1.setTrasa(0);
        TrasaWycieczkiEntity tripRoute2 = new TrasaWycieczkiEntity();
        tripRoute2.setWycieczka(0);
        tripRoute2.setTrasa(1);
        TrasaEntity route1 = new TrasaEntity();
        route1.setNumer(0);
        route1.setPodgrupa("6");
        TrasaEntity route2 = new TrasaEntity();
        route2.setPodgrupa("6");
        route2.setNumer(1);
        List<TrasaWycieczkiEntity> tripRoutes = new ArrayList<>();
        tripRoutes.add(tripRoute1);
        tripRoutes.add(tripRoute2);


        when(repositoryWycieczka.findById(trip1.getNumer())).thenReturn(Optional.of(trip1));
        when(repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(trip1.getNumer())).thenReturn(tripRoutes);
        when(repositoryTrasa.findById(tripRoute1.getNumer())).thenReturn(Optional.of(route1));
        when(repositoryTrasa.findById(tripRoute2.getNumer())).thenReturn(Optional.of(route2));
        when(repositoryPodgrupa.findById(route1.getPodgrupa())).thenThrow(new SubgroupNotFoundException(route1.getPodgrupa()));

        SubgroupNotFoundException ex = assertThrows(SubgroupNotFoundException.class, () ->
                service.sendTripToVerification(trip1.getNumer()));

        assertThat(ex.getMessage()).isEqualTo("Could not find subgroup " + route1.getPodgrupa());
    }


}