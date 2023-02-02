package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.enums.EntityName;
import com.ivandavid.spotify.exception.ResourceNotFoundException;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.service.GenreService;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ivandavid.spotify.enums.EntityName.TRACK;
import static com.ivandavid.spotify.enums.SearchParamType.ID;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final TrackService trackService;

    public GenreServiceImpl(GenreRepository genreRepository, TrackService trackService) {
        this.genreRepository = genreRepository;
        this.trackService = trackService;
    }

    @Override
    public GenreDTO create(GenreDTO dto) {
        var tracks = dto.getTrackIds().stream().map(trackService::getTrackEntityById).toList();
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
            throw new ResourceNotFoundException(TRACK, ID);
        return genres.stream().map(GenreDTO::fromEntity).toList();
    }

    @Override
    public GenreDTO findById(Long id) {
        var genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.GENRE, ID, id));
        return GenreDTO.fromEntity(genre);
    }

    @Override
    public Genre getGenreEntityById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(EntityName.GENRE, ID, id));
    }

    @Override
    public List<TrackDTO> findTracksByGenreId(Long genreId) {
        var genre = getGenreEntityById(genreId);
        var tracks = trackService.findTracksByGenre(genre);
        return tracks.stream().map(TrackDTO::fromEntity).toList();
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
        var genre = getGenreEntityById(id);
        genre.setName(name);
        return GenreDTO.fromEntity(genre);
    }

    @Override
    public void deleteById(Long id) {
        var genre = getGenreEntityById(id);
        genreRepository.delete(genre);
    }

}
