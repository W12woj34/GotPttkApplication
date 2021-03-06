package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.SubgroupReplyDTO;
import com.example.got_pttk_po.exceptions.SubgroupNotFoundException;
import com.example.got_pttk_po.repositories.PodgrupaRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PodgrupaService {

    private final PodgrupaRepository repositoryPodgrupa;

    PodgrupaService(PodgrupaRepository repository) {

        this.repositoryPodgrupa = repository;
    }

    /**
     * @return TripReplyDTO List with subgroups data
     */
    public List<SubgroupReplyDTO> getAllSubgroups() {

        return repositoryPodgrupa.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, SubgroupReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of mountain group
     * @return TripReplyDTO List with subgroups data
     */
    public List<SubgroupReplyDTO> getAllSubgroupsFromGroup(String id) {

        return repositoryPodgrupa.findByGrupa(id).stream()
                .map(el -> ModelMapperUtil.map(el, SubgroupReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of subgroup
     * @return SubgroupReplyDTO object with subgroup data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public SubgroupReplyDTO getOneSubgroup(String id) {

        return repositoryPodgrupa.findById(id).map(el -> ModelMapperUtil.map(el, SubgroupReplyDTO.class))
                .orElseThrow(() -> new SubgroupNotFoundException(id));
    }



}
