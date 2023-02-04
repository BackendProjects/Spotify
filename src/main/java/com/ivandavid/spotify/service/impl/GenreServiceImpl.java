package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.enums.EntityName;
import com.ivandavid.spotify.enums.ExceptionMessage;
import com.ivandavid.spotify.exception.BadRequestException;
import com.ivandavid.spotify.exception.ResourceNotFoundException;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.service.GenreService;
import com.ivandavid.spotify.service.TrackService;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ivandavid.spotify.enums.EntityName.GENRE;
import static com.ivandavid.spotify.enums.EntityName.TRACK;
import static com.ivandavid.spotify.enums.ExceptionMessage.NAME_ALREADY_TAKEN;
import static com.ivandavid.spotify.enums.ExceptionMessage.NOT_INPUT_ELEMENT;
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
    public GenreDTO createGenre(GenreDTO dto) {
        if (dto.getName().isEmpty()) {
            throw new BadRequestException(NOT_INPUT_ELEMENT.value);
        }
        if (genreRepository.existsGenreByName(dto.getName())) {
            throw new BadRequestException(NAME_ALREADY_TAKEN.value);
        }
        var genre = new Genre(
                dto.getName()
        );
        var storedGenre = genreRepository.save(genre);
        return GenreDTO.fromEntity(storedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        var genres = genreRepository.findAll();
        if (genres.isEmpty()) {
            throw new ResourceNotFoundException(GENRE.value);
        }
        return genres.stream().map(GenreDTO::fromEntity).toList();
    }

    @Override
    public GenreDTO getGenreById(Long id) {
        var genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(GENRE.value, ID, id));
        return GenreDTO.fromEntity(genre);
    }

    @Override
    public Genre getGenreEntityById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(GENRE.value, ID, id));
    }

    @Override
    public List<TrackDTO> GetTracksByGenreId(Long genreId) {
        var genre = getGenreEntityById(genreId);
        var tracks = trackService.getTracksByGenre(genre);
        return tracks.stream().map(TrackDTO::fromEntity).toList();
    }

    @Override
    public GenreDTO updateGenre(Long id, GenreDTO dto) {
        var genre = getGenreEntityById(id);
        genre.setName(dto.getName());
        var sotoredGenre = genreRepository.save(genre);
        return GenreDTO.fromEntity(sotoredGenre);
    }

    @Override
    public GenreDTO updateName(Long id, String name) {
        var genre = getGenreEntityById(id);
        genre.setName(name);
        return GenreDTO.fromEntity(genre);
    }

    @Transactional
    @Override
    public void deleteGenreById(Long id) {

        var genre = getGenreEntityById(id);
        var tracks = trackService.getTracksByGenre(genre);
        for (Track track : tracks) {
            trackService.deleteTrackById(track.getId());
        }
        genreRepository.delete(genre);
        //@Qualifier
        //@Component(name="algo")
        //@Qualifier("algo")
        //DTO con Component
    }

}
