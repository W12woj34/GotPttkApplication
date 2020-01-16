package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.RouteReplyDTO;
import com.example.got_pttk_po.entities.TrasaEntity;
import com.example.got_pttk_po.exceptions.RouteNotFoundException;
import com.example.got_pttk_po.repositories.TrasaRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TrasaService {

    private final TrasaRepository repositoryTrasa;

    TrasaService(TrasaRepository repository) {

        this.repositoryTrasa = repository;
    }

    /**
     * @return RouteReplyDTO List with routes data
     */
    public List<RouteReplyDTO> getAllRoutes() {

        return repositoryTrasa.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, RouteReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of route
     * @return RouteReplyDTO List with possible routes data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<RouteReplyDTO> getPossibleRoutes(Integer id) {

       TrasaEntity route =  repositoryTrasa.findById(id).orElseThrow(() -> new RouteNotFoundException(id));
        return repositoryTrasa.findByPoczatkowy(route.getKoncowy()).stream()
                .map(el -> ModelMapperUtil.map(el, RouteReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of route
     * @return RouteReplyDTO object with route data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public RouteReplyDTO getOneRoute(Integer id) {

        return repositoryTrasa.findById(id).map(el -> ModelMapperUtil.map(el, RouteReplyDTO.class))
                .orElseThrow(() -> new RouteNotFoundException(id));
    }

    /**
     * @param id     Id of route
     * @return  RouteReplyDTO List with routes data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public List<RouteReplyDTO> getAllRoutesInSubgroup(String id) {
        return repositoryTrasa.findByPodgrupa(id).stream()
                .map(el -> ModelMapperUtil.map(el, RouteReplyDTO.class))
                .collect(Collectors.toList());
    }

}
