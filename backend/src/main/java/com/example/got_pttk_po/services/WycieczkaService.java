package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.*;
import com.example.got_pttk_po.exceptions.*;
import com.example.got_pttk_po.repositories.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


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


    public List<WycieczkaEntity> getAllTrips() {

        return repositoryWycieczka.findAll();
    }

    public WycieczkaEntity getOneTrip(Integer id) {

        return repositoryWycieczka.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
    }

    public List<WycieczkaEntity> getAllTripBadge(Integer id) {

        return repositoryWycieczka.findByOdznaka(id);
    }

    public List<WycieczkaEntity> getAllTripsTourist(String id) {

        return repositoryWycieczka.findByOdznakaIn(getBadgesIdsFromTouristId(id));
    }

    public List<WycieczkaEntity> getAllTripsTouristStatus(String id, Integer status) {


        return repositoryWycieczka.findByOdznakaInAndStatus(getBadgesIdsFromTouristId(id), status);
    }

    private List<Integer> getBadgesIdsFromTouristId(String id) {
        List<ZdobywanaOdznakaEntity> badges = repositoryZdobywanaOdznaka.findByTurysta(id);
        List<Integer> badgesIds = new ArrayList<>();
        for (ZdobywanaOdznakaEntity badge : badges) {
            badgesIds.add(badge.getId());
        }
        return badgesIds;
    }

    public List<WycieczkaEntity> getAllTripsLeaderStatus(String id, Integer status) {

        return repositoryWycieczka.findByPrzodownikAndStatus(id, status);
    }

    public List<WycieczkaEntity> getAllTripsLeader(String id) {

        return repositoryWycieczka.findByPrzodownik(id);
    }

    public WycieczkaEntity addTrip(Integer newTripGetBadge) {

        ZdobywanaOdznakaEntity badge = repositoryZdobywanaOdznaka.findById(newTripGetBadge)
                .orElseThrow(() -> new GetBadgeNotFoundException(newTripGetBadge));

        if (badge.getStatus() == 0 || badge.getStatus() == 3) {

            WycieczkaEntity trip = new WycieczkaEntity();
            trip.setStatus(0);
            trip.setOdznaka(newTripGetBadge);
            trip.setDataRozpoczecia(new Date(0));
            trip.setDataZakonczenia(new Date(0));

            return repositoryWycieczka.save(trip);
        } else {

            throw new GetBadgeIsVerificatedException(newTripGetBadge);
        }


    }

    public WycieczkaEntity sendTripToVerification(Integer id) {

        WycieczkaEntity trip = repositoryWycieczka.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));

        changeTripStatus(trip.getNumer(), 3);
        List<String> groupIds = new ArrayList<>();
        List<PodgrupaEntity> subgroups = new ArrayList<>();
        List<String> subgroupIds = new ArrayList<>();
        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczka(id);
        List<TrasaEntity> routes = new ArrayList<>();

        for (TrasaWycieczkiEntity tripRoute : tripRoutes) {
            routes.add(repositoryTrasa.findById(tripRoute.getWycieczka())
                    .orElseThrow(() -> new RouteNotFoundException(tripRoute.getWycieczka())));
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
                return repositoryWycieczka.save(trip);
            }
        }
        throw new TripCannotBeVerifiedException(trip.getNumer());
    }

    public List<WycieczkaEntity> sendTripsToVerification(List<Integer> ids) {

        List<WycieczkaEntity> sentTrips = new ArrayList<>();
        for (Integer id : ids) {
            try {
                sendTripToVerification(id);
                sentTrips.add(repositoryWycieczka.findById(id)
                        .orElseThrow(() -> new TripNotFoundException(id)));

            } catch (RuntimeException ignored) {

            }
        }
        return sentTrips;
    }



    public WycieczkaEntity changeTripStatus(Integer id, Integer status) {

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

        return repositoryWycieczka.save(trip);
    }

    public Integer deleteIrip(Integer id) {

        WycieczkaEntity trip = repositoryWycieczka.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
        if (trip.getStatus() == 0 || trip.getStatus() == 2) {

            List<TrasaWycieczkiEntity> routes = repositoryTrasaWycieczki.findByWycieczka(id);
            serviceTrasaWycieczki.deleteRoutes(routes);

            repositoryWycieczka.deleteById(id);
        } else {
            throw new TripCannotBeDeletedException(id);
        }
        return id;
    }

    public List<Integer> deleteTrips(List<Integer> ids) {
        List<Integer> idsToDelete = new ArrayList<>();
        for (Integer id : ids) {
            WycieczkaEntity trip = repositoryWycieczka.findById(id)
                    .orElseThrow(() -> new TripNotFoundException(id));
            if (trip.getStatus() == 0 || trip.getStatus() == 2) {

                idsToDelete.add(trip.getNumer());

                List<TrasaWycieczkiEntity> routes = repositoryTrasaWycieczki.findByWycieczka(id);
                serviceTrasaWycieczki.deleteRoutes(routes);

                repositoryWycieczka.deleteById(trip.getNumer());
            }
        }
        return idsToDelete;
    }


}
