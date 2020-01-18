package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.MountainGroupReplyDTO;
import com.example.got_pttk_po.entities.PodgrupaEntity;
import com.example.got_pttk_po.entities.TrasaEntity;
import com.example.got_pttk_po.entities.TrasaWycieczkiEntity;
import com.example.got_pttk_po.exceptions.GroupNotFoundException;
import com.example.got_pttk_po.exceptions.TripNotFoundException;
import com.example.got_pttk_po.repositories.*;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GrupaGorskaService {

    private final GrupaGorskaRepository repositoryGrupaGorska;
    private final WycieczkaRepository repositoryWycieczka;
    private final TrasaWycieczkiRepository repositoryTrasaWycieczki;
    private final PodgrupaRepository repositoryPodgrupa;
    private final TrasaRepository repositoryTrasa;

    GrupaGorskaService(GrupaGorskaRepository repository, WycieczkaRepository repositoryWycieczka, TrasaWycieczkiRepository repositoryTrasaWycieczki, PodgrupaRepository repositoryPodgrupa, TrasaRepository repositoryTrasa) {

        this.repositoryGrupaGorska = repository;
        this.repositoryWycieczka = repositoryWycieczka;
        this.repositoryTrasaWycieczki = repositoryTrasaWycieczki;
        this.repositoryPodgrupa = repositoryPodgrupa;
        this.repositoryTrasa = repositoryTrasa;
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

    /**
     * @param id Id of trip
     * @return String List with groups ids
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<MountainGroupReplyDTO> getGroups(Integer id) {

        repositoryWycieczka.findById(id).orElseThrow(() -> new TripNotFoundException(id));
        List<TrasaWycieczkiEntity> tripRoutes = repositoryTrasaWycieczki.findByWycieczkaOrderByDataDesc(id);
        List<Integer> routesIds = new ArrayList<>();
        for(TrasaWycieczkiEntity tripRoute : tripRoutes){
            routesIds.add(tripRoute.getTrasa());
        }
        List<TrasaEntity> routes = repositoryTrasa.findByNumerIn(routesIds);
        List<String> subgroupsIds = new ArrayList<>();
        for(TrasaEntity route : routes){
            subgroupsIds.add(route.getPodgrupa());
        }
        List<PodgrupaEntity> subgroups = repositoryPodgrupa.findByIdIn(subgroupsIds);
        List<String> groups = new ArrayList<>();
        for(PodgrupaEntity subgroup : subgroups){
            if(!groups.contains(subgroup.getGrupa())){
                groups.add(subgroup.getGrupa());
            }
        }
        return repositoryGrupaGorska.findByNazwaIn(groups).stream()
                .map(el -> ModelMapperUtil.map(el, MountainGroupReplyDTO.class))
                .collect(Collectors.toList());

    }

}
