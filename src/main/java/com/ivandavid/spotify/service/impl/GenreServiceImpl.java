package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.enums.EntityName;
import com.ivandavid.spotify.exception.ResourceNotFoundException;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.repository.TrackRepository;
import com.ivandavid.spotify.service.GenreService;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ivandavid.spotify.enums.SearchParamType.ID;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;
    private TrackRepository trackRepository;
    private TrackService trackService;

    public GenreServiceImpl(GenreRepository genreRepository, TrackRepository trackRepository, TrackService trackService) {
        this.genreRepository = genreRepository;
        this.trackRepository = trackRepository;
        this.trackService = trackService;
    }

    @Override
    public GenreDTO create(GenreDTO dto) {
        var tracks = dto.getTrackIds().stream().map(id -> trackRepository.findById(id).get()).toList();
        var genre = new Genre(
                dto.getName(),
                tracks
        );
        var storedGenre = genreRepository.save(genre);
        return GenreDTO.fromEntity(storedGenre);
    }

    @Override
    public List<GenreDTO> findAll() {
        var genres = genreRepository.findAll();
        if (genres.isEmpty())
            return null;
        var genresDTO = new ArrayList<GenreDTO>();
        for (Genre g : genres)
            genresDTO.add(GenreDTO.fromEntity(g));
        return genresDTO;
    }

    @Override
    public GenreDTO findById(Long id) {
        var genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.GENRE, ID, id));
        return GenreDTO.fromEntity(genre);
    }

    @Override
    public List<TrackDTO> findTracksByGenreId(Long genreId) {
        var genre = genreRepository.findById(genreId).get();
        var tracks = trackService.findTracksByGenre(genre);
        var trackDTOs = new ArrayList<TrackDTO>();
        for (Track t : tracks)
            trackDTOs.add(TrackDTO.fromEntity(t));
        return trackDTOs;
    }

    @Override
    public GenreDTO update(Long id, GenreDTO dto) {
        var genre = genreRepository.findById(id);
        if (genre.isEmpty())
            return null;
        genre.get().setName(dto.getName());
        return GenreDTO.fromEntity(genre.get());
    }

    @Override
    public GenreDTO updateName(Long id, String name) {
        var genre = genreRepository.findById(id);
        if (genre.isEmpty())
            return null;
        genre.get().setName(name);
        return GenreDTO.fromEntity(genre.get());
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

}
