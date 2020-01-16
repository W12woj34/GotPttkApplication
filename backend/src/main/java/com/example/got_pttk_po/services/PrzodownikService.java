package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.LeaderReplyDTO;
import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.entities.PrzodownikEntity;
import com.example.got_pttk_po.exceptions.LeaderNotFoundException;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.PrzodownikRepository;
import com.example.got_pttk_po.repositories.UzytkownikRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PrzodownikService {

    private final PrzodownikRepository repositoryPrzodownik;
    private final UzytkownikRepository repositoryUzytkownik;

    PrzodownikService(PrzodownikRepository repositoryPrzodownik, UzytkownikRepository repositoryUzytkownik) {
        this.repositoryPrzodownik = repositoryPrzodownik;
        this.repositoryUzytkownik = repositoryUzytkownik;
    }

    /**

     * @return LeaderReplyDTO object with leader id
     */
    public List<LeaderReplyDTO> getAllLeaders() {

        return repositoryPrzodownik.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, LeaderReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of leader
     * @return LeaderReplyDTO object with leader id
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public LeaderReplyDTO getOneLeader(String id) {

        return repositoryPrzodownik.findById(id).map(el -> ModelMapperUtil.map(el, LeaderReplyDTO.class))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * @return UserReplyDTO List with leaders data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<UserReplyDTO> getAllLeadersAll() {

        List<PrzodownikEntity> leadersList = repositoryPrzodownik.findAll();
        List<String> leaderIds = new ArrayList<>();
        for (PrzodownikEntity tourit : leadersList) {
            repositoryUzytkownik.findById(tourit.getNrLicencji()).orElseThrow(() -> new UserNotFoundException(tourit.getNrLicencji()));
            leaderIds.add(tourit.getNrLicencji());
        }
        return repositoryUzytkownik.findAllById(leaderIds).stream()
                .map(el -> ModelMapperUtil.map(el, UserReplyDTO.class))
                .collect(Collectors.toList());
    }


    /**
     * @param id     Id of leader
     * @return UserReplyDTO object with leaders data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public UserReplyDTO getOneLeaderAll(String id) {

        PrzodownikEntity leader = repositoryPrzodownik.findById(id).orElseThrow(() -> new LeaderNotFoundException(id));
        return repositoryUzytkownik.findById(leader.getNrLicencji())
                .map(el -> ModelMapperUtil.map(el, UserReplyDTO.class)).orElseThrow(() -> new UserNotFoundException(id));
    }

}