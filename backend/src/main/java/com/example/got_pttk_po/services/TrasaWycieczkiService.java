package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.RouteReplyDTO;
import com.example.got_pttk_po.dto.TripRouteAddDTO;
import com.example.got_pttk_po.dto.TripRouteUpdateDTO;
import com.example.got_pttk_po.dto.TripRouteReplyDTO;
import com.example.got_pttk_po.entities.TrasaEntity;
import com.example.got_pttk_po.entities.TrasaWycieczkiEntity;
import com.example.got_pttk_po.entities.WycieczkaEntity;
import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import com.example.got_pttk_po.exceptions.*;
import com.example.got_pttk_po.repositories.*;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TrasaWycieczkiService {

    private final TrasaWycieczkiRepository repositoryTrasaWycieczki;
    private final WycieczkaRepository repositoryWycieczka;
    private final TrasaRepository repositoryTrasa;
    private final ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;
    private final OdznakaRepository repositoryOdznaka;
    private final TrasaService serviceTrasa;

    TrasaWycieczkiService(TrasaWycieczkiRepository repositoryTrasaWycieczki, WycieczkaRepository repositoryWycieczka,
                          TrasaRepository repositoryTrasa, ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka,
                          OdznakaRepository repositoryOdznaka, TrasaService serviceTrasa) {
        this.repositoryTrasaWycieczki = repositoryTrasaWycieczki;
        this.repositoryWycieczka = repositoryWycieczka;
        this.repositoryTrasa = repositoryTrasa;
        this.repositoryZdobywanaOdznaka = repositoryZdobywanaOdznaka;
        this.repositoryOdznaka = repositoryOdznaka;
        this.serviceTrasa = serviceTrasa;
    }

    /**
     * @return TripRouteReplyDTO List with trip routes data
     */
    public List<TripRouteReplyDTO> getAllTripRoutes() {

        return repositoryTrasaWycieczki.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, TripRouteReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id Id of trip route
     * @return TripRouteReplyDTO object with trip route data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public TripRouteReplyDTO getOneTripRoute(Integer id) {

        return repositoryTrasaWycieczki.findById(id).map(el -> ModelMapperUtil.map(el, TripRouteReplyDTO.class))
                .orElseThrow(() -> new TripRouteNotFoundException(id));
    }

    /**
     * @param id Id of  trip route
     * @return TripRouteReplyDTO List with trip routes data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<TripRouteReplyDTO> getAllTripRoutesForTrip(Integer id) {

        return repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id).stream()
                .map(el -> ModelMapperUtil.map(el, TripRouteReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param newTripRoute TripRouteReplyDTO object with initial trip route data
     * @return TripRouteReplyDTO object with trip route data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public TripRouteReplyDTO addTripRoute(TripRouteAddDTO newTripRoute) {
        TrasaWycieczkiEntity tripRoute = new TrasaWycieczkiEntity();
        WycieczkaEntity trip = repositoryWycieczka.findById(newTripRoute.getTrip())
                .orElseThrow(() -> new TripNotFoundException(newTripRoute.getTrip()));

        List<TrasaWycieczkiEntity> badgeRoutes = getAllTripRoutesForGetBadge(trip.getOdznaka());

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
            repositoryTrasaWycieczki.save(tripRoute);

            return ModelMapperUtil.map(tripRoute, TripRouteReplyDTO.class);
        } else {
            List<RouteReplyDTO> possibleRoutes = serviceTrasa.getPossibleRoutes(newTripRoute.getRoute());
            List<Integer> possibleRouteIds = new ArrayList<>();
            for (RouteReplyDTO possibleRoute : possibleRoutes) {
                possibleRouteIds.add(possibleRoute.getNumer());
            }
            if (!possibleRouteIds.contains(newTripRoute.getRoute())) {
                throw new TripRouteInvalidException(-1);
            }

            if (newTripRoute.getDate().compareTo(tripRoutes.get(0).getData()) < 0) {
                throw new TripRouteInvalidException(-1);
            }

            tripRoute.setIndeks(tripRoutes.size());
            tripRoute.setWycieczka(newTripRoute.getTrip());
            tripRoute.setData(newTripRoute.getDate());
            tripRoute.setTrasa(newTripRoute.getRoute());

            repositoryTrasaWycieczki.save(tripRoute);
            return ModelMapperUtil.map(tripRoute, TripRouteReplyDTO.class);
        }
    }

    /**
     * @param newTripRoute TripRouteUpdateDTO object with trip route data to modify
     * @param id           Id of trip route
     * @return TripRouteReplyDTO object with trip route data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public TripRouteReplyDTO modifyTripRoute(TripRouteUpdateDTO newTripRoute, Integer id) {

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
        repositoryTrasaWycieczki.save(tripRoute);
        return ModelMapperUtil.map(tripRoute, TripRouteReplyDTO.class);
    }

    /**
     * @param id Id of trip route
     * @return Id of deleted trip route
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public Integer deleteTripRoute(Integer id) {
        TrasaWycieczkiEntity tripRoute = repositoryTrasaWycieczki.findById(id)
                .orElseThrow(() -> new TripRouteNotFoundException(id));
        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(tripRoute.getWycieczka());
        if (tripRoute.getIndeks() != tripRoutes.size() - 1) {
            throw new TripRouteInvalidException(id);
        }

        repositoryTrasaWycieczki.deleteById(id);
        WycieczkaEntity trip = repositoryWycieczka.findById(tripRoute.getWycieczka())
                .orElseThrow(() -> new TripNotFoundException(tripRoute.getWycieczka()));
        updateTripDate(trip.getNumer());
        updatePowtozonaProperty(trip.getOdznaka());
        recalculateGetBadgePoints(trip.getOdznaka());
        return id;
    }

    /**
     * @param ids List of trip routes ids
     * @return Integer List with deleted trip routes ids
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<Integer> deleteTripRoutes(List<Integer> ids) {
        List<TrasaWycieczkiEntity> deleteTripRoutes = repositoryTrasaWycieczki.findByNumerIn(ids);
        if (deleteTripRoutes.size() != ids.size()) {
            throw new TripRouteInvalidException(-1);
        }
        TrasaWycieczkiEntity route = repositoryTrasaWycieczki.findById(ids.get(0))
                .orElseThrow(() -> new TripRouteNotFoundException(ids.get(0)));
        WycieczkaEntity trip = repositoryWycieczka.findById(route.getWycieczka())
                .orElseThrow(() -> new TripNotFoundException(route.getWycieczka()));
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
        updatePowtozonaProperty(trip.getOdznaka());
        recalculateGetBadgePoints(trip.getOdznaka());
        return ids;
    }

    /**
     * @param getBadgeId Id of user badge
     */
    @Transactional
    protected void updatePowtozonaProperty(int getBadgeId) {

        List<TrasaWycieczkiEntity> tripRoutes = getAllTripRoutesForGetBadge(getBadgeId);
        List<Integer> routeIds = new ArrayList<>();
        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            routeIds.add(tripRoute.getTrasa());
        }
        Collections.sort(tripRoutes);
        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            if (routeIds.contains(tripRoute.getTrasa())) {
                tripRoute.setPowtozona(true);
            } else {
                tripRoute.setPowtozona(false);
            }
            repositoryTrasaWycieczki.save(tripRoute);
        }

    }

    /**
     * @param getBadgeId of user badge
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    private void recalculateGetBadgePoints(Integer getBadgeId) {

        getAllTripRoutesForGetBadge(getBadgeId);
        List<TrasaWycieczkiEntity> tripRoutes = getAllTripRoutesForGetBadge(getBadgeId);

        ZdobywanaOdznakaEntity getBadge = repositoryZdobywanaOdznaka.findById(getBadgeId)
                .orElseThrow(() -> new GetBadgeNotFoundException(getBadgeId));

        List<ZdobywanaOdznakaEntity> previousBadges = repositoryZdobywanaOdznaka
                .findByTurystaAndStatusOrderByDataZdobyciaDesc(getBadge.getTurysta(), 1);

        int points;
        if (previousBadges.isEmpty() || previousBadges.get(0).getOdznaka().contains("Za Wytrwałość")) {
            points = 0;
        } else {
            points = previousBadges.get(0).getPunkty() - repositoryOdznaka.findById(getBadge.getOdznaka())
                    .orElseThrow(() -> new BadgeNotFoundException(getBadge.getOdznaka())).getWymaganePunkty();
        }


        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            if (!tripRoute.isPowtozona()) {
                TrasaEntity route = repositoryTrasa.findById(tripRoute.getTrasa())
                        .orElseThrow(() -> new RouteNotFoundException(tripRoute.getTrasa()));
                points = points + route.getPunkty();
            }
        }

        getBadge.setPunkty(points);
        repositoryZdobywanaOdznaka.save(getBadge);
    }

    /**
     * @param id     Id of user badge
     * @return TripRouteReplyDTO List with trip routes data
     */
    private List<TrasaWycieczkiEntity> getAllTripRoutesForGetBadge(Integer id) {
        List<WycieczkaEntity> trips = repositoryWycieczka.findByOdznaka(id);
        List<Integer> tripIds = new ArrayList<>();
        for (WycieczkaEntity trip : trips) {
            tripIds.add(trip.getNumer());
        }

        return repositoryTrasaWycieczki.findByWycieczkaIn(tripIds);
    }

    /**
     * @param id     Id of trip route
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    private void updateTripDate(Integer id) {

        WycieczkaEntity trip = repositoryWycieczka.findById(id)
                .orElseThrow(() -> new RouteNotFoundException(id));

        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id);

        if (tripRoutes.isEmpty()) {
            trip.setDataRozpoczecia(null);
            trip.setDataZakonczenia(null);
        } else {
            trip.setDataRozpoczecia(tripRoutes.get(tripRoutes.size() - 1).getData());
            trip.setDataZakonczenia(tripRoutes.get(0).getData());
        }

        repositoryWycieczka.save(trip);
    }
}



