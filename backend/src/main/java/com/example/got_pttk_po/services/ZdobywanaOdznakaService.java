package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.BadgeReplyDTO;
import com.example.got_pttk_po.dto.GetBadgeReplyDTO;
import com.example.got_pttk_po.entities.OdznakaEntity;
import com.example.got_pttk_po.entities.WycieczkaEntity;
import com.example.got_pttk_po.entities.ZdobywanaOdznakaEntity;
import com.example.got_pttk_po.exceptions.BadgeNotFoundException;
import com.example.got_pttk_po.exceptions.BadgeNotPossibleException;
import com.example.got_pttk_po.exceptions.GetBadgeIsVerificatedException;
import com.example.got_pttk_po.exceptions.GetBadgeNotFoundException;
import com.example.got_pttk_po.repositories.OdznakaRepository;
import com.example.got_pttk_po.repositories.WycieczkaRepository;
import com.example.got_pttk_po.repositories.ZdobywanaOdznakaRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ZdobywanaOdznakaService {

    private final ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka;
    private final OdznakaRepository repositoryOdznaka;
    private final WycieczkaRepository repositoryWycieczka;
    private final WycieczkaService wycieczkaService;

    ZdobywanaOdznakaService(ZdobywanaOdznakaRepository repositoryZdobywanaOdznaka, OdznakaRepository repositoryOdznaka,
                            WycieczkaRepository repositoryWycieczka, WycieczkaService wycieczkaService) {
        this.repositoryZdobywanaOdznaka = repositoryZdobywanaOdznaka;
        this.repositoryOdznaka = repositoryOdznaka;
        this.repositoryWycieczka = repositoryWycieczka;
        this.wycieczkaService = wycieczkaService;
    }

    /**
     * @return GetBadgeReplyDTO oList with users badges data
     */
    public List<GetBadgeReplyDTO> getAllGetBadges() {

        return repositoryZdobywanaOdznaka.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, GetBadgeReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id Id of tourist
     * @return GetBadgeReplyDTO List with users badge sdata
     */
    public List<GetBadgeReplyDTO> getAllGetBadgesTourist(String id) {

        return repositoryZdobywanaOdznaka.findByTurysta(id).stream()
                .map(el -> ModelMapperUtil.map(el, GetBadgeReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id Id of user badge
     * @param status User badge status
     * @return GetBadgeReplyDTO List with users badges data
     */
    public List<GetBadgeReplyDTO> getAllGetBadgesTouristStatus(String id, Integer status) {

        return repositoryZdobywanaOdznaka.findByTurystaAndStatus(id, status).stream()
                .map(el -> ModelMapperUtil.map(el, GetBadgeReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id Id of user badge
     * @return GetBadgeReplyDTO object with user badge data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public GetBadgeReplyDTO getOneGetBadge(Integer id) {

        return repositoryZdobywanaOdznaka.findById(id).map(el -> ModelMapperUtil.map(el, GetBadgeReplyDTO.class))
                .orElseThrow(() -> new GetBadgeNotFoundException(id));
    }

    /**
     * @param id Id of tourist
     * @return BadgeReplyDTO list of possible badges
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<BadgeReplyDTO> getAllPossibleBadgesTourist(String id) {
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
        return possibleBadges.stream()
                .map(el -> ModelMapperUtil.map(el, BadgeReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param touristId Id of tourist
     * @param badgeId Name of badge
     * @return GetBadgeReplyDTO object with user badge data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public GetBadgeReplyDTO addGetBadge(String touristId, String badgeId) {
        List<ZdobywanaOdznakaEntity> currentGetBadge = repositoryZdobywanaOdznaka.findByTurystaAndStatus(touristId, 0);
        if (!currentGetBadge.isEmpty()) {
            throw new BadgeNotPossibleException(badgeId);
        }
        ZdobywanaOdznakaEntity badge = new ZdobywanaOdznakaEntity();
        List<BadgeReplyDTO> possibleBadges = getAllPossibleBadgesTourist(touristId);
        List<String> possibleIds = new ArrayList<>();
        for (BadgeReplyDTO possibleBadge : possibleBadges) {
            possibleIds.add(possibleBadge.getNazwa());
        }
        if (!possibleIds.contains(badgeId)) {
            throw new BadgeNotPossibleException(badgeId);
        }

        badge.setStatus(0);
        badge.setTurysta(touristId);
        badge.setOdznaka(badgeId);
        List<ZdobywanaOdznakaEntity> previousBadges = repositoryZdobywanaOdznaka
                .findByTurystaAndStatusOrderByDataZdobyciaDesc(touristId, 1);
        if (previousBadges.isEmpty() || previousBadges.get(0).getOdznaka().contains("Za Wytrwałość")) {
            badge.setPunkty(0);
        } else {
            badge.setPunkty(previousBadges.get(0).getPunkty() - repositoryOdznaka.findById(badgeId)
                    .orElseThrow(() -> new BadgeNotFoundException(badgeId)).getWymaganePunkty());
        }

        repositoryZdobywanaOdznaka.save(badge);
        return ModelMapperUtil.map(badge, GetBadgeReplyDTO.class);
    }

    /**
     * @param id Id of user badge
     * @return Integer id of deleted badge
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public Integer deleteGetBadge(Integer id) {

        ZdobywanaOdznakaEntity badge = repositoryZdobywanaOdznaka.findById(id)
                .orElseThrow(() -> new GetBadgeNotFoundException(id));
        if (badge.getStatus() == 1 || badge.getStatus() == 2) {
            throw new GetBadgeIsVerificatedException(id);
        }

        List<Integer> tripIds = new ArrayList<>();
        List<WycieczkaEntity> trips = repositoryWycieczka.findByOdznaka(id);
        for (WycieczkaEntity trip : trips) {
            tripIds.add(trip.getNumer());
        }
        wycieczkaService.deleteTrips(tripIds);
        repositoryZdobywanaOdznaka.deleteById(id);
        return id;
    }

    /**
     * @param getBadgeId Id of user badge
     * @param newBadgeId Name of new badge
     * @return GetBadgeReplyDTO object with user badge data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public GetBadgeReplyDTO changeBadge(Integer getBadgeId, String newBadgeId) {

        ZdobywanaOdznakaEntity currentBadge = repositoryZdobywanaOdznaka
                .findById(getBadgeId).orElseThrow(() -> new GetBadgeNotFoundException(getBadgeId));
        OdznakaEntity newBadge = repositoryOdznaka.findById(newBadgeId)
                .orElseThrow(() -> new BadgeNotFoundException(newBadgeId));
        List<BadgeReplyDTO> possibleBadges = getAllPossibleBadgesTourist(currentBadge.getTurysta());
        List<String> possibleBadgesIds = new ArrayList<>();
        for (BadgeReplyDTO possibleBadge : possibleBadges) {
            possibleBadgesIds.add(possibleBadge.getNazwa());
        }
        if (possibleBadgesIds.contains(newBadge.getNazwa())) {
            currentBadge.setOdznaka(newBadgeId);
            repositoryZdobywanaOdznaka.save(currentBadge);
            return ModelMapperUtil.map(currentBadge, GetBadgeReplyDTO.class);
        } else {
            throw new BadgeNotPossibleException(newBadgeId);
        }
    }

    /**
     * @param ownBadges  List of own badges
     * @param possibleBadges List of possible badges
     */
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

    /**
     * @param possibleBadges  List of possible badges
     * @param badge Name of specific badge
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    private void addPossibleBadge(List<OdznakaEntity> possibleBadges, String badge) {

        possibleBadges.add(repositoryOdznaka.findById(badge)
                .orElseThrow(() -> new BadgeNotFoundException(badge)));
    }

    /**
     * @param ownBadges  List of own badges
     * @param badge Name of specific badge
     * @return is list of bagges contain specific badge
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    private boolean checkContainsBadge(List<OdznakaEntity> ownBadges, String badge) {

        return ownBadges.contains(repositoryOdznaka.findById(badge)
                .orElseThrow(() -> new BadgeNotFoundException(badge)));
    }


}


