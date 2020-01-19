package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.TripReplyDTO;
import com.example.got_pttk_po.entities.*;
import com.example.got_pttk_po.exceptions.*;
import com.example.got_pttk_po.repositories.*;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class WycieczkaService {

    private final WycieczkaRepository repositoryWycieczka;
    private final ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;
    private final TrasaWycieczkiRepository repositoryTrasaWycieczki;
    private final TrasaRepository repositoryTrasa;
    private final PodgrupaRepository repositoryPodgrupa;
    private final UprawnieniaPrzodownikaRepository repositoryUprawnieniaPrzodownika;
    private final TrasaWycieczkiService serviceTrasaWycieczki;

    WycieczkaService(WycieczkaRepository repositoryWycieczka, ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka,
                     TrasaWycieczkiRepository repositoryTrasaWycieczki, TrasaRepository repositoryTrasa,
                     PodgrupaRepository repositoryPodgrupa, UprawnieniaPrzodownikaRepository repositoryUprawnieniaPrzodownika,
                     TrasaWycieczkiService serviceTrasaWycieczki) {
        this.repositoryWycieczka = repositoryWycieczka;
        this.repositoryZdobywanaOdznaka = repositoryZdobywanaOdznaka;
        this.repositoryTrasaWycieczki = repositoryTrasaWycieczki;
        this.repositoryTrasa = repositoryTrasa;
        this.repositoryPodgrupa = repositoryPodgrupa;
        this.repositoryUprawnieniaPrzodownika = repositoryUprawnieniaPrzodownika;
        this.serviceTrasaWycieczki = serviceTrasaWycieczki;
    }

    /**
     * @return TripReplyDTO object with trip data
     */
    public List<TripReplyDTO> getAllTrips() {

        return repositoryWycieczka.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id Id of trip
     * @return TripReplyDTO object with trip data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public TripReplyDTO getOneTrip(Integer id) {

        return repositoryWycieczka.findById(id).map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .orElseThrow(() -> new TripNotFoundException(id));
    }

    /**
     * @param id Id of user badge
     * @return TripReplyDTO List with trips data
     */
    public List<TripReplyDTO> getAllTripBadge(Integer id) {

        return repositoryWycieczka.findByOdznaka(id).stream()
                .map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id Id of tourist
     * @return TripReplyDTO List with trips data
     */
    public List<TripReplyDTO> getAllTripsTourist(String id) {

        return repositoryWycieczka.findByOdznakaIn(getBadgesIdsFromTouristId(id)).stream()
                .map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of tourist
     * @param status Specific status
     * @return TripReplyDTO List with trips data
     */
    public List<TripReplyDTO> getAllTripsTouristStatus(String id, Integer status) {
        return repositoryWycieczka.findByOdznakaInAndStatus(getBadgesIdsFromTouristId(id), status).stream()
                .map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of leader
     * @param status Specific status
     * @return TripReplyDTO List with trips data
     */
    public List<TripReplyDTO> getAllTripsLeaderStatus(String id, Integer status) {

        return repositoryWycieczka.findByPrzodownikAndStatus(id, status).stream()
                .map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id Id of leader
     * @return TripReplyDTO List with trips data
     */
    public List<TripReplyDTO> getAllTripsLeader(String id) {

        return repositoryWycieczka.findByPrzodownik(id).stream()
                .map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param newTripGetBadge Id of user badge
     * @return TripReplyDTO object with trip data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public TripReplyDTO addTrip(Integer newTripGetBadge) {

        ZdobywanaOdznakaEntity badge = repositoryZdobywanaOdznaka.findById(newTripGetBadge)
                .orElseThrow(() -> new GetBadgeNotFoundException(newTripGetBadge));

        if (badge.getStatus() == 0 || badge.getStatus() == 3) {

            WycieczkaEntity trip = new WycieczkaEntity();
            trip.setStatus(0);
            trip.setOdznaka(newTripGetBadge);

            repositoryWycieczka.save(trip);
            return ModelMapperUtil.map(trip, TripReplyDTO.class);
        } else {

            throw new GetBadgeIsVerificatedException(newTripGetBadge);
        }


    }

    /**
     * @param id Id of trip
     * @return TripReplyDTO object with trip data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public TripReplyDTO sendTripToVerification(Integer id) {

        WycieczkaEntity trip = repositoryWycieczka.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));

        List<String> groupIds = new ArrayList<>();
        List<PodgrupaEntity> subgroups = new ArrayList<>();
        List<String> subgroupIds = new ArrayList<>();
        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id);
        List<TrasaEntity> routes = new ArrayList<>();

        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            routes.add(repositoryTrasa.findById(tripRoute.getTrasa())
                    .orElseThrow(() -> new RouteNotFoundException(tripRoute.getTrasa())));
        }

        for (TrasaEntity route : routes) {
            if (!subgroupIds.contains(route.getPodgrupa())) {
                subgroupIds.add(route.getPodgrupa());
            }
        }

        for (String subgroupId : subgroupIds) {
            subgroups.add(repositoryPodgrupa.findById(subgroupId)
                    .orElseThrow(() -> new SubgroupNotFoundException(subgroupId)));
        }

        for (PodgrupaEntity subgroup : subgroups) {
            if (!groupIds.contains(subgroup.getGrupa())) {
                groupIds.add(subgroup.getGrupa());
            }
        }

        List<UprawnieniaPrzodownikaEntity> permissions = repositoryUprawnieniaPrzodownika.findByGrupaIn(groupIds);
        List<String> leaders = new ArrayList<>();
        Collections.shuffle(leaders);
        for (UprawnieniaPrzodownikaEntity permission : permissions) {
            leaders.add(permission.getPrzodownik());
        }

        HashMap<String, Integer> fitness = new HashMap<>();
        for (String leader : leaders) {
            fitness.putIfAbsent(leader, 0);
        }
        for (String leader : leaders) {
            fitness.put(leader, fitness.get(leader) + 1);
        }

        for (String leader : leaders) {
            if (fitness.get(leader) == groupIds.size()) {
                trip.setPrzodownik(leader);
                changeTripStatus(trip.getNumer(), 3);
                repositoryWycieczka.save(trip);
                return ModelMapperUtil.map(trip, TripReplyDTO.class);
            }
        }
        throw new TripCannotBeVerifiedException(trip.getNumer());
    }

    /**
     * @param ids List of trips ids
     * @return TripReplyDTO List with user trips data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<TripReplyDTO> sendTripsToVerification(List<Integer> ids) {

        List<WycieczkaEntity> sentTrips = new ArrayList<>();
        for (Integer id : ids) {
            try {
                sendTripToVerification(id);
                sentTrips.add(repositoryWycieczka.findById(id)
                        .orElseThrow(() -> new TripNotFoundException(id)));

            } catch (RuntimeException ignored) {

            }
        }

        return sentTrips.stream()
                .map(el -> ModelMapperUtil.map(el, TripReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of trip
     * @param status New trip status
     * @return TripReplyDTO object with trip data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public TripReplyDTO changeTripStatus(Integer id, Integer status) {

        WycieczkaEntity trip = repositoryWycieczka.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));

        if (status == 0) {
            if (trip.getStatus() == 0 || trip.getStatus() == 2) {
                trip.setStatus(status);
            } else {
                throw new TripStatusCannotChangeException(id);
            }
        } else if (status == 1 || status == 2) {
            if (trip.getStatus() == 3) {
                trip.setStatus(status);
            } else {
                throw new TripStatusCannotChangeException(id);
            }
        } else if (status == 3) {
            if (trip.getStatus() == 0) {
                trip.setStatus(status);
            } else {
                throw new TripStatusCannotChangeException(id);
            }

        } else {
            throw new TripStatusCannotChangeException(id);
        }

        repositoryWycieczka.save(trip);
        return ModelMapperUtil.map(trip, TripReplyDTO.class);
    }

    /**
     * @param id Id of trip
     * @return Id of deleted trip
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public Integer deleteTrip(Integer id) {

        WycieczkaEntity trip = repositoryWycieczka.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
        if (trip.getStatus() == 0 || trip.getStatus() == 2) {

            deleteAllRoutesForTrip(id);

            repositoryWycieczka.deleteById(id);
        } else {
            throw new TripCannotBeDeletedException(id);
        }
        return id;
    }

    /**
     * @param ids List of trips ids
     * @return Integer List of deleted trips ids
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<Integer> deleteTrips(List<Integer> ids) {
        List<Integer> idsToDelete = new ArrayList<>();
        for (Integer id : ids) {
            WycieczkaEntity trip = repositoryWycieczka.findById(id)
                    .orElseThrow(() -> new TripNotFoundException(id));
            if (trip.getStatus() == 0 || trip.getStatus() == 2) {

                idsToDelete.add(trip.getNumer());

                deleteAllRoutesForTrip(id);

                repositoryWycieczka.deleteById(trip.getNumer());
            }
        }
        return idsToDelete;
    }

    /**
     * @param id Id of trip
     * @return Number of points
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public Integer getPoints(Integer id) {

        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id);
        int points = 0;
        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            if (!tripRoute.isPowtozona()) {
                TrasaEntity route = repositoryTrasa.findById(tripRoute.getTrasa())
                        .orElseThrow(() -> new RouteNotFoundException(tripRoute.getTrasa()));
                points = points + route.getPunkty();
            }
        }
        return points;
    }

    /**
     * @param id Id of trip
     */
    private void deleteAllRoutesForTrip(Integer id) {
        List<TrasaWycieczkiEntity> routes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id);
        List<Integer> routeIds = new ArrayList<>();
        for (TrasaWycieczkiEntity route : routes) {
            routeIds.add(route.getNumer());
        }
        serviceTrasaWycieczki.deleteTripRoutes(routeIds);
    }

    /**
     * @param id Id of tourist
     * @return Integer List with user badges ids
     */
    private List<Integer> getBadgesIdsFromTouristId(String id) {
        List<ZdobywanaOdznakaEntity> badges = repositoryZdobywanaOdznaka.findByTurysta(id);
        List<Integer> badgesIds = new ArrayList<>();
        for (ZdobywanaOdznakaEntity badge : badges) {
            badgesIds.add(badge.getId());
        }
        return badgesIds;
    }

}
