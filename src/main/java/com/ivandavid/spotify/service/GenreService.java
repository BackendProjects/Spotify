package com.ivandavid.spotify.service;

import com.ivandavid.spotify.DTO.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO create(GenreDTO dto);

    List<GenreDTO> findAll();

    GenreDTO findById(Long id);

    GenreDTO updateById(Long id, GenreDTO dto);
    GenreDTO updateNameById(Long id, String name);

    void deleteById(Long id);


}
