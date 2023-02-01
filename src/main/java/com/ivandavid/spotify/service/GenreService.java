package com.ivandavid.spotify.service;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;

import java.util.List;

public interface GenreService {

    GenreDTO create(GenreDTO dto);

    List<GenreDTO> findAll();

    GenreDTO findById(Long id);
    Genre getGenreEntityById(Long id);
    List<TrackDTO> findTracksByGenreId(Long genreId);

    GenreDTO update(Long id, GenreDTO dto);
    GenreDTO updateName(Long id, String name);

    void deleteById(Long id);


}
