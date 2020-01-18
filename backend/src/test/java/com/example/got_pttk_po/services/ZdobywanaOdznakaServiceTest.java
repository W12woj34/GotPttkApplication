package com.example.got_pttk_po.services;


import com.example.got_pttk_po.dto.*;
import com.example.got_pttk_po.entities.*;
import com.example.got_pttk_po.exceptions.BadgeNotPossibleException;
import com.example.got_pttk_po.exceptions.TouristNotFoundException;
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
public class ZdobywanaOdznakaServiceTest {
    private ZdobywanaOdznakaEntity getBadge;

    @Mock
    OdznakaRepository repositoryOdznaka;
    @Mock
    ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;
    @Mock
    TurystaRepository repositoryTurysta;

    @InjectMocks
    ZdobywanaOdznakaService service;

    @Before
    public void setUp() {
        getBadge = new ZdobywanaOdznakaEntity();
        getBadge.setId(0);
        getBadge.setOdznaka("Popularna");
        getBadge.setTurysta("1");
        getBadge.setStatus(0);
    }


    @Test
    public void shouldReturnGetBadgeIfAdd() {
        OdznakaEntity badge = new OdznakaEntity();
                badge.setNazwa("Popularna");
        NewGetBadgeDTO newGetBadge = new NewGetBadgeDTO();
        newGetBadge.setBadgeId("Popularna");
        newGetBadge.setTouristId("1");
        when(repositoryTurysta.findById(getBadge.getTurysta())).thenReturn(Optional.of(new TurystaEntity()));
        when(repositoryZdobywanaOdznaka.findByTurystaAndStatus(newGetBadge.getTouristId(), 0)).thenReturn(new ArrayList<>());
        when(repositoryZdobywanaOdznaka
                .findByTurystaAndStatusOrderByDataZdobyciaDesc(newGetBadge.getTouristId(), 1)).thenReturn(new ArrayList<>());

        when( repositoryOdznaka.findByNazwaIn(new ArrayList<>())).thenReturn(new ArrayList<>());
        when( repositoryOdznaka.findById(newGetBadge.getBadgeId())).thenReturn(Optional.of(badge));
        GetBadgeReplyDTO addGetBadge = service.addGetBadge(newGetBadge.getTouristId(), newGetBadge.getBadgeId());

        assertThat(addGetBadge.getId()).isEqualTo(getBadge.getId());
        assertThat(addGetBadge.getOdznaka()).isEqualTo(getBadge.getOdznaka());
        assertThat(addGetBadge.getPunkty()).isEqualTo(0);
        assertThat(addGetBadge.getData_zdobycia()).isEqualTo(null);
        assertThat(addGetBadge.getReferat()).isEqualTo(null);
        assertThat(addGetBadge.getStatus()).isEqualTo(0);
        assertThat(addGetBadge.getTurysta()).isEqualTo(getBadge.getTurysta());
    }

    @Test
    public void shouldThrowExceptionIfWrongTouristId() {
        when(repositoryTurysta.findById(getBadge.getTurysta())).thenThrow(new TouristNotFoundException(getBadge.getTurysta()));

        TouristNotFoundException ex = assertThrows(TouristNotFoundException.class, () ->
                service.addGetBadge(getBadge.getTurysta(), getBadge.getOdznaka()));

        assertThat(ex.getMessage()).isEqualTo("Could not find tourist " + getBadge.getTurysta());
    }

    @Test
    public void shouldThrowExceptionIfCurrentGetBadgeExist() {
        List<ZdobywanaOdznakaEntity> currentBadge = new ArrayList<>();
        currentBadge.add(getBadge);
        when(repositoryTurysta.findById(getBadge.getTurysta())).thenReturn(Optional.of(new TurystaEntity()));
        when(repositoryZdobywanaOdznaka.findByTurystaAndStatus(getBadge.getTurysta(), 0)).thenReturn(currentBadge);

        BadgeNotPossibleException ex = assertThrows(BadgeNotPossibleException.class, () ->
                service.addGetBadge(getBadge.getTurysta(), getBadge.getOdznaka()));

        assertThat(ex.getMessage()).isEqualTo("Could not add badge " + getBadge.getOdznaka());
    }

    @Test
    public void shouldReturnPossibleBadgesIfEmpty() {
        OdznakaEntity badge = new OdznakaEntity();
        badge.setNazwa("Popularna");
        List<ZdobywanaOdznakaEntity> currentGetBadges = new ArrayList<>();
        List<OdznakaEntity> currentBadges = new ArrayList<>();
        List<String> currentGetBadgesIds = new ArrayList<>();

        when(repositoryTurysta.findById("1")).thenReturn(Optional.of(new TurystaEntity()));
        when(repositoryZdobywanaOdznaka.findByTurysta("1")).thenReturn(currentGetBadges);
        when(repositoryOdznaka.findByNazwaIn(currentGetBadgesIds)).thenReturn(currentBadges);
        when(repositoryOdznaka.findById("Popularna")).thenReturn(Optional.of(badge));

        List<BadgeReplyDTO> addGetBadge = service.getAllPossibleBadgesTourist("1");
        assertThat(addGetBadge.size() == 1);
        assertThat(addGetBadge.get(0).getNazwa().equals("Popularna"));

    }


    @Test
    public void shouldThrowExceptionIfWrongTourist() {

        when(repositoryTurysta.findById(getBadge.getTurysta())).thenThrow(new TouristNotFoundException(getBadge.getTurysta()));

        TouristNotFoundException ex = assertThrows(TouristNotFoundException.class, () ->
                service.getAllPossibleBadgesTourist(getBadge.getTurysta()));

        assertThat(ex.getMessage()).isEqualTo("Could not find tourist " + getBadge.getTurysta());
    }





}