package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.TrasaWycieczkiAddDTO;
import com.example.got_pttk_po.dto.TrasaWycieczkiUpdateDTO;
import com.example.got_pttk_po.entities.TrasaEntity;
import com.example.got_pttk_po.entities.TrasaWycieczkiEntity;
import com.example.got_pttk_po.entities.WycieczkaEntity;
import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import com.example.got_pttk_po.exceptions.*;
import com.example.got_pttk_po.repositories.TrasaRepository;
import com.example.got_pttk_po.repositories.TrasaWycieczkiRepository;
import com.example.got_pttk_po.repositories.WycieczkaRepository;
import com.example.got_pttk_po.repositories.ZdobywanaOdznakaRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class TrasaWycieczkiService {

    private final TrasaWycieczkiRepository repositoryTrasaWycieczki;
    private final WycieczkaRepository repositoryWycieczka;
    private final TrasaRepository repositoryTrasa;
    private final ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;
    private final TrasaService serviceTrasa;

    TrasaWycieczkiService(TrasaWycieczkiRepository repositoryTrasaWycieczki, WycieczkaRepository repositoryWycieczka,
                          TrasaRepository repositoryTrasa, ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka,
                          TrasaService serviceTrasa) {
        this.repositoryTrasaWycieczki = repositoryTrasaWycieczki;
        this.repositoryWycieczka = repositoryWycieczka;
        this.repositoryTrasa = repositoryTrasa;
        this.repositoryZdobywanaOdznaka = repositoryZdobywanaOdznaka;
        this.serviceTrasa = serviceTrasa;
    }


    public List<TrasaWycieczkiEntity> getAllTripRoutes() {

        return repositoryTrasaWycieczki.findAll();
    }

    public TrasaWycieczkiEntity getOneTripRoute(Integer id) {

        return repositoryTrasaWycieczki.findById(id)
                .orElseThrow(() -> new TripRouteNotFoundException(id));
    }


    public List<TrasaWycieczkiEntity> getAllTripRoutesForTrip(Integer id) {

        return repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id);
    }

    public TrasaWycieczkiEntity addTripRoute(TrasaWycieczkiAddDTO newTripRoute) {
        TrasaWycieczkiEntity tripRoute = new TrasaWycieczkiEntity();
        WycieczkaEntity trip = repositoryWycieczka.findById(newTripRoute.getTrip())
                .orElseThrow(() -> new TripNotFoundException(newTripRoute.getTrip()));
        List<WycieczkaEntity> trips = repositoryWycieczka.findByOdznaka(trip.getOdznaka());
        List<Integer> tripIds = new ArrayList<>();
        for (WycieczkaEntity oneTrip : trips) {
            tripIds.add(oneTrip.getNumer());
        }

        List<TrasaWycieczkiEntity> badgeRoutes = repositoryTrasaWycieczki.findByWycieczkaIn(tripIds);
        List<Integer> badgeRouteIds = new ArrayList<>();
        for (TrasaWycieczkiEntity badgeRoute : badgeRoutes) {
            badgeRouteIds.add(badgeRoute.getTrasa());
        }

        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(newTripRoute.getTrip());

        if (badgeRouteIds.contains(newTripRoute.getRoute())) {
            tripRoute.setPowtozona(true);
        } else {
            tripRoute.setPowtozona(false);
        }
        if (tripRoutes.isEmpty()) {
            tripRoute.setIndeks(0);
            tripRoute.setData(newTripRoute.getDate());
            tripRoute.setWycieczka(newTripRoute.getTrip());
            tripRoute.setTrasa(newTripRoute.getRoute());
            return repositoryTrasaWycieczki.save(tripRoute);
        } else {
            List<TrasaEntity> possibleRoutes = serviceTrasa.getPossibleRoutes(newTripRoute.getRoute());
            List<Integer> possibleRouteIds = new ArrayList<>();
            for (TrasaEntity possibleRoute : possibleRoutes) {
                possibleRouteIds.add(possibleRoute.getNumer());
            }
            if (!possibleRouteIds.contains(newTripRoute.getRoute())) {
                throw new TripRouteInvalidException(-1);
            }

            if (newTripRoute.getDate().compareTo(tripRoutes.get(0).getData()) < 0) {
                throw new TripRouteInvalidException(-1);
            }

            tripRoute.setIndeks(tripRoutes.size());
            tripRoute.setData(newTripRoute.getDate());
            tripRoute.setWycieczka(newTripRoute.getTrip());
            tripRoute.setTrasa(newTripRoute.getRoute());

            return repositoryTrasaWycieczki.save(tripRoute);
        }
    }

    public TrasaWycieczkiEntity modifyTripRoute(TrasaWycieczkiUpdateDTO newTripRoute, Integer id) {

        Date newDate = newTripRoute.getDate();
        TrasaWycieczkiEntity tripRoute = repositoryTrasaWycieczki.findById(id)
                .orElseThrow(() -> new TripRouteNotFoundException(id));

        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(tripRoute.getWycieczka());
        for (int i = 0; i < tripRoutes.size(); i++) {

            if (tripRoutes.get(i).getNumer() == tripRoute.getNumer()) {
                if (i == 0) {
                    if (i + 1 != tripRoutes.size()) {
                        if (newDate.compareTo(tripRoutes.get(i + 1).getData()) > 0) {
                            throw new TripRouteInvalidException(id);
                        }
                        tripRoute.setData(newDate);
                    } else {
                        tripRoute.setData(newDate);
                    }

                } else {
                    if (i + 1 != tripRoutes.size()) {
                        if (newDate.compareTo(tripRoutes.get(i + 1).getData()) > 0 ||
                                newDate.compareTo(tripRoutes.get(i - 1).getData()) < 0) {
                            throw new TripRouteInvalidException(id);
                        }
                        tripRoute.setData(newDate);
                    } else {
                        if (newDate.compareTo(tripRoutes.get(i - 1).getData()) < 0) {
                            throw new TripRouteInvalidException(id);
                        }
                        tripRoute.setData(newDate);
                    }
                }
            }
        }
        return repositoryTrasaWycieczki.save(tripRoute);
    }

    public Integer deleteTripRoute(Integer id) {
        TrasaWycieczkiEntity tripRoute = repositoryTrasaWycieczki.findById(id)
                .orElseThrow(() -> new TripRouteNotFoundException(id));
        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(tripRoute.getWycieczka());
        if (tripRoute.getIndeks() != tripRoutes.size()) {
            throw new TripRouteInvalidException(id);
        }

        repositoryTrasaWycieczki.deleteById(id);
        WycieczkaEntity trip = repositoryWycieczka.findById(tripRoute.getWycieczka())
                .orElseThrow(() -> new TripNotFoundException(tripRoute.getWycieczka()));
        updateTripDate(trip.getNumer());
        recalculateGetBadgePoints(trip.getOdznaka());
        return id;
    }

    public List<Integer> deleteTripRoutes(List<Integer> ids) {
        List<TrasaWycieczkiEntity> deleteTripRoutes = repositoryTrasaWycieczki.findByNumerIn(ids);
        if (deleteTripRoutes.size() != ids.size()) {
            throw new TripRouteInvalidException(-1);
        }
        TrasaWycieczkiEntity route = repositoryTrasaWycieczki.findById(ids.get(0))
                .orElseThrow(() -> new TripRouteNotFoundException(ids.get(0)));
        WycieczkaEntity trip = repositoryWycieczka.findById(route.getWycieczka())
                .orElseThrow(() -> new TripNotFoundException(ids.get(0)));
        int tripId = trip.getNumer();
        for (TrasaWycieczkiEntity deleteTrip : deleteTripRoutes) {
            if (deleteTrip.getWycieczka() != tripId) {
                throw new TripRouteInvalidException(deleteTrip.getNumer());
            }
        }
        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(trip.getNumer());
        for (int i = 0; i < tripRoutes.size(); i++) {
            if (deleteTripRoutes.contains(tripRoutes.get(i))) {
                tripRoutes.remove(i);
                i--;
            }
        }

        List<Integer> indexes = new ArrayList<>();
        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            indexes.add(tripRoute.getIndeks());
        }
        Collections.sort(indexes);
        if (indexes.size() != 0) {
            if (indexes.get(indexes.size() - 1) != indexes.size() - 1) {
                throw new TripRouteInvalidException(-1);
            }
        }
        repositoryTrasaWycieczki.deleteByNumerIn(ids);
        updateTripDate(trip.getNumer());
        recalculateGetBadgePoints(trip.getOdznaka());
        return ids;
    }

    private void recalculateGetBadgePoints(Integer id) {

        List<Integer> tripIds = new ArrayList<>();
        List<WycieczkaEntity> trips = repositoryWycieczka.findByOdznaka(id);
        for (WycieczkaEntity trip : trips) {
            tripIds.add(trip.getNumer());
        }

        int points = 0;
        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaIn(tripIds);

        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            if (!tripRoute.isPowtozona()) {
                TrasaEntity route = repositoryTrasa.findById(tripRoute.getTrasa())
                        .orElseThrow(() -> new RouteNotFoundException(tripRoute.getTrasa()));
                points = points + route.getPunkty();
            }
        }

        ZdobywanaOdznakaEntity getBadge = repositoryZdobywanaOdznaka.findById(id)
                .orElseThrow(() -> new GetBadgeNotFoundException(id));
        getBadge.setPunkty(points);
        repositoryZdobywanaOdznaka.save(getBadge);
    }

    private void updateTripDate(Integer id) {

        WycieczkaEntity trip = repositoryWycieczka.findById(id)
                .orElseThrow(() -> new RouteNotFoundException(id));

        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id);

        if (tripRoutes.isEmpty()) {
            trip.setDataRozpoczecia(null);
            trip.setDataZakonczenia(null);
        }
        else{
            trip.setDataRozpoczecia(tripRoutes.get(tripRoutes.size()-1).getData());
            trip.setDataZakonczenia(tripRoutes.get(0).getData());
        }

        repositoryWycieczka.save(trip);
    }
}



