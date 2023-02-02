package com.ivandavid.spotify.service;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;

import java.util.List;

public interface GenreService {

    GenreDTO createGenre(GenreDTO dto);

    List<GenreDTO> getAllGenres();

    GenreDTO getGenreById(Long id);
    Genre getGenreEntityById(Long id);
    List<TrackDTO> GetTracksByGenreId(Long genreId);

    GenreDTO updateGenre(Long id, GenreDTO dto);
    GenreDTO updateName(Long id, String name);

    void deleteGenreById(Long id);


}
