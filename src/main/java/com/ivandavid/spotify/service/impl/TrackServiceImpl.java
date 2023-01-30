package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.exception.TrackListNotFoundException;
import com.ivandavid.spotify.exception.TrackNotFoundException;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.repository.TrackRepository;
import com.ivandavid.spotify.service.GenreService;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final GenreRepository genreRepository;

    public TrackServiceImpl(TrackRepository trackRepository, GenreRepository genreRepository) {
        this.trackRepository = trackRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public TrackDTO create(TrackDTO dto) {
        var genres = dto.getGenreIds().stream().map(id -> genreRepository.findById(id).get()).toList();
        var track = new Track(
                dto.getName(),
                dto.getDuration(),
                genres
        );
        var storedTrack = trackRepository.save(track);
        return TrackDTO.fromEntity(storedTrack);
    }

    @Override
    public List<TrackDTO> findAll() {
        var tracks = trackRepository.findAll();
        if (tracks.isEmpty())
            throw new TrackListNotFoundException();
        var trackDTOS = new ArrayList<TrackDTO>();
        for (Track track : tracks)
            trackDTOS.add(TrackDTO.fromEntity(track));
        return trackDTOS;
    }

    @Override
    public TrackDTO findById(Long id) {
        var track = trackRepository.findById(id)
                .orElseThrow(() -> new TrackNotFoundException(id));
        return TrackDTO.fromEntity(track);
    }

    @Override
    public TrackDTO update(Long id, TrackDTO dto) {

        var track = trackRepository.findById(id).get();
        track.setName(dto.getName());
        track.setDuration(dto.getDuration());
        track.setReleasedDate(dto.getReleasedDate());

        List<Genre> genres = new ArrayList<>();
        for (Long genreId : dto.getGenreIds()) {
            var gen = genreRepository.findById(genreId).get();
            genres.add(gen);
        }
        track.setGenres(genres);

        var storedTrack = trackRepository.save(track);
        return TrackDTO.fromEntity(storedTrack);
    }

    @Override
    public TrackDTO updateDuration(Long id, Long duration) {
        var track = trackRepository.findById(id);
        if (track.isEmpty())
            return null;
        track.get().setDuration(duration);
        return TrackDTO.fromEntity(track.get());
    }

    @Override
    public void delete(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public List<Track> findTracksByGenre(Genre genre) {
        return trackRepository.findTracksByGenre(genre);
    }

}
