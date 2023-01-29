package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.exception.TrackNotFoundException;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.repository.TrackRepository;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            return null;
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
        var track = trackRepository.findById(id);
        if (track.isEmpty())
            return null;
        track.get().setName(dto.getName());
        track.get().setDuration(dto.getDuration());
        track.get().setReleasedDate(dto.getReleasedDate());
        return TrackDTO.fromEntity(track.get());
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
