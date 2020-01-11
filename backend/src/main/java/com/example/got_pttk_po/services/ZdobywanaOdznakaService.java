package com.example.got_pttk_po.services;

import com.example.got_pttk_po.entities.OdznakaEntity;
import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import com.example.got_pttk_po.exceptions.BadgeNotFoundException;
import com.example.got_pttk_po.exceptions.BadgeNotPossibleException;
import com.example.got_pttk_po.exceptions.GetBadgeIsVerificatedException;
import com.example.got_pttk_po.exceptions.GetBadgeNotFoundException;
import com.example.got_pttk_po.repositories.OdznakaRepository;
import com.example.got_pttk_po.repositories.ZdobywanaOdznakaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ZdobywanaOdznakaService {

    private final ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;
    private final OdznakaRepository repositoryOdznaka;

    ZdobywanaOdznakaService(ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka, OdznakaRepository repositoryOdznaka) {
        this.repositoryZdobywanaOdznaka = repositoryZdobywanaOdznaka;
        this.repositoryOdznaka = repositoryOdznaka;
    }

    public List<ZdobywanaOdznakaEntity> getAllGetBadges() {

        return repositoryZdobywanaOdznaka.findAll();
    }

    public List<ZdobywanaOdznakaEntity> getAllGetBadgesTourist(String id) {

        return repositoryZdobywanaOdznaka.findByTurysta(id);
    }

    public List<ZdobywanaOdznakaEntity> getAllGetBadgesTouristStatus(String id, Integer status) {

        return repositoryZdobywanaOdznaka.findByTurystaAndStatus(id, status);
    }

    public ZdobywanaOdznakaEntity getOneGetBadge(Integer id) {

        return repositoryZdobywanaOdznaka.findById(id)
                .orElseThrow(() -> new GetBadgeNotFoundException(id));
    }

    public List<OdznakaEntity> getAllPossibleBadgesTourist(String id) {
        List<ZdobywanaOdznakaEntity> ownGetBadges = repositoryZdobywanaOdznaka.findByTurysta(id);
        List<String> ownGetBadgesIds = new ArrayList<>();
        for (ZdobywanaOdznakaEntity getBadge : ownGetBadges) {
            if (getBadge.getStatus() == 1) {
                ownGetBadgesIds.add(getBadge.getOdznaka());
            }
        }
        List<OdznakaEntity> ownBadges = repositoryOdznaka.findByNazwaIn(ownGetBadgesIds);
        List<OdznakaEntity> possibleBadges = new ArrayList<>();

        if (ownBadges.isEmpty()) {
            addPossibleBadge(possibleBadges, "Popularna");

        } else if (checkContainsBadge(ownBadges, "Za Wytrwałość - mała")) {
            addPossibleBadge(possibleBadges, "Za Wytrwałość - Duża");

            if (checkContainsBadge(ownBadges, "Za Wytrwałość - Duża")) {
                addCheckPossible(ownBadges, possibleBadges);

            } else {
                addCheckPossible(ownBadges, possibleBadges);
            }

        } else {
            if (checkContainsBadge(ownBadges, "Mała Złota")) {
                addPossibleBadge(possibleBadges, "Za Wytrwałość - mała");
            }
            addCheckPossible(ownBadges, possibleBadges);
        }
        return possibleBadges;
    }

    public ZdobywanaOdznakaEntity addGetBadge(String touristId, String badgeId) {
        ZdobywanaOdznakaEntity badge = new ZdobywanaOdznakaEntity();
        List<OdznakaEntity> possibleBadges = getAllPossibleBadgesTourist(touristId);
        List<String> possibleIds = new ArrayList<>();
        for (OdznakaEntity possibleBadge : possibleBadges) {
            possibleIds.add(possibleBadge.getNazwa());
        }
        if (!possibleIds.contains(badgeId)) {
            throw new BadgeNotPossibleException(badgeId);
        }

        badge.setStatus(0);
        badge.setTurysta(touristId);
        badge.setOdznaka(badgeId);
        List<ZdobywanaOdznakaEntity> previousBadges = repositoryZdobywanaOdznaka.findByTurystaOrderByDataZdobyciaDesc(touristId);
        if (previousBadges.isEmpty() || previousBadges.get(0).getOdznaka().contains("Za Wytrwałość")) {
            badge.setPunkty(0);
        } else {
            badge.setPunkty(previousBadges.get(0).getPunkty() - repositoryOdznaka.findById(badgeId)
                    .orElseThrow(() -> new BadgeNotFoundException(badgeId)).getWymaganePunkty());
        }

        return repositoryZdobywanaOdznaka.save(badge);
    }

    public Integer deleteGetBadge(Integer id) {

        ZdobywanaOdznakaEntity badge = repositoryZdobywanaOdznaka.findById(id)
                .orElseThrow(() -> new GetBadgeNotFoundException(id));
        if (badge.getStatus() == 0) {
            throw new GetBadgeIsVerificatedException(id);
        }
        repositoryZdobywanaOdznaka.deleteById(id);
        return id;
    }

    public ZdobywanaOdznakaEntity changeBadge(Integer getBadgeId, String newBadgeId) {

        ZdobywanaOdznakaEntity currentBadge = repositoryZdobywanaOdznaka
                .findById(getBadgeId).orElseThrow(() -> new GetBadgeNotFoundException(getBadgeId));
        OdznakaEntity newBadge = repositoryOdznaka.findById(newBadgeId)
                .orElseThrow(() -> new BadgeNotFoundException(newBadgeId));
        List<OdznakaEntity> possibleBadges = getAllPossibleBadgesTourist(currentBadge.getTurysta());
        List<String> possibleBadgesIds = new ArrayList<>();
        for (OdznakaEntity possibleBadge : possibleBadges) {
            possibleBadgesIds.add(possibleBadge.getNazwa());
        }
        if (possibleBadgesIds.contains(newBadge.getNazwa())) {
            currentBadge.setOdznaka(newBadgeId);
            return repositoryZdobywanaOdznaka.save(currentBadge);
        } else {
            throw new BadgeNotPossibleException(newBadgeId);
        }
    }

    private void addCheckPossible(List<OdznakaEntity> ownBadges, List<OdznakaEntity> possibleBadges) {
        if (!checkContainsBadge(ownBadges, "Duża Złota")) {
            if (checkContainsBadge(ownBadges, "Duża Srebrna")) {
                addPossibleBadge(possibleBadges, "Duża Złota");

            } else if (checkContainsBadge(ownBadges, "Duża Brązowa")) {
                addPossibleBadge(possibleBadges, "Duża Srebrna");

            } else if (checkContainsBadge(ownBadges, "Mała Złota")) {
                addPossibleBadge(possibleBadges, "Duża Brązowa");

            } else if (checkContainsBadge(ownBadges, "Mała Srebrna")) {
                addPossibleBadge(possibleBadges, "Mała Złota");

            } else if (checkContainsBadge(ownBadges, "Mała Brązowa")) {
                addPossibleBadge(possibleBadges, "Mała Srebrna");

            } else if (checkContainsBadge(ownBadges, "Popularna")) {
                addPossibleBadge(possibleBadges, "Mała Brązowa");
            }
        }
    }

    private void addPossibleBadge(List<OdznakaEntity> possibleBadges, String badge) {

        possibleBadges.add(repositoryOdznaka.findById(badge)
                .orElseThrow(() -> new BadgeNotFoundException(badge)));
    }

    private boolean checkContainsBadge(List<OdznakaEntity> ownBadges, String badge) {

        return ownBadges.contains(repositoryOdznaka.findById(badge)
                .orElseThrow(() -> new BadgeNotFoundException(badge)));
    }


}


