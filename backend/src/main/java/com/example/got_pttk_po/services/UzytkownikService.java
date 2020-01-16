package com.example.got_pttk_po.services;

import com.example.got_pttk_po.dto.UserReplyDTO;
import com.example.got_pttk_po.exceptions.UserNotFoundException;
import com.example.got_pttk_po.repositories.UzytkownikRepository;
import com.example.got_pttk_po.utils.ModelMapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UzytkownikService {

    private final UzytkownikRepository repository;

    UzytkownikService(UzytkownikRepository repository) {
        this.repository = repository;
    }

    /**
     * @return UserReplyDTO List with users data
     */
    public List<UserReplyDTO> getAllUsers() {

        return repository.findAll().stream()
                .map(el -> ModelMapperUtil.map(el, UserReplyDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param id     Id of user
     * @return UserReplyDTO List with user data
     * @throws RuntimeException when one of given or needed elements don't exist
     */
    public UserReplyDTO getOneUser(String id) {

        return repository.findById(id).map(el -> ModelMapperUtil.map(el, UserReplyDTO.class))
                .orElseThrow(() -> new UserNotFoundException(id));
    }



}
