package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.BadgeReplyDTO;
import com.example.got_pttk_po.exceptions.BadgeNotFoundException;
import com.example.got_pttk_po.repositories.OdznakaRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OdznakaService {

    private final OdznakaRepository repositoryOdznaka;

    OdznakaService(OdznakaRepository repository) {

        this.repositoryOdznaka = repository;
    }

    /**
     * @return BadgeReplyDTO List with badges data
     */
    public List<BadgeReplyDTO> getAllBadges() {

        return repositoryOdznaka.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, BadgeReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of badge
     * @return BadgeReplyDTO object with badge data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public BadgeReplyDTO getOneBadge(String id) {

        return repositoryOdznaka.findById(id).map(el -> ModelMapperUtil.map(el, BadgeReplyDTO.class))
                .orElseThrow(() -> new BadgeNotFoundException(id));
    }



}
