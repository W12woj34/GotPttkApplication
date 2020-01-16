package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.MountainGroupReplyDTO;
import com.example.got_pttk_po.exceptions.GroupNotFoundException;
import com.example.got_pttk_po.repositories.GrupaGorskaRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GrupaGorskaService {

    private final GrupaGorskaRepository repositoryGrupaGorska;

    GrupaGorskaService(GrupaGorskaRepository repository) {

        this.repositoryGrupaGorska = repository;
    }

    /**
     * @return MountainGroupReplyDTO List with mountain group data
     */
    public List<MountainGroupReplyDTO> getAllGroups() {

        return repositoryGrupaGorska.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, MountainGroupReplyDTO.class))
                .collect(Collectors.toList());

    }


    /**
     * @param id     Id of trip
     * @return MountainGroupReplyDTO object with mountain group data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public MountainGroupReplyDTO getOneGroup(String id) {

        return repositoryGrupaGorska.findById(id).map(el -> ModelMapperUtil.map(el, MountainGroupReplyDTO.class))
                .orElseThrow(() -> new GroupNotFoundException(id));
    }



}
