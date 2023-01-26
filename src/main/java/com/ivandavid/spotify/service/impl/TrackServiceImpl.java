package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.repository.TrackRepository;
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
        Track track = new Track(
                dto.getName(),
                dto.getDuration(),
                genres
        );
        Track storedTrack = trackRepository.save(track);
        return TrackDTO.fromEntity(storedTrack);
    }

    @Override
    public List<TrackDTO> findAll() {
        List<Track> tracks = trackRepository.findAll();
        if (tracks.isEmpty())
            return null;
        List<TrackDTO> trackDTOS = new ArrayList<>();
        for (Track track : tracks)
            trackDTOS.add(TrackDTO.fromEntity(track));
        return trackDTOS;
    }

    @Override
    public TrackDTO findById(Long id) {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isEmpty())
            return null;
        return TrackDTO.fromEntity(track.get());
    }

    @Override
    public TrackDTO update(Long id, TrackDTO dto) {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isEmpty())
            return null;
        track.get().setName(dto.getName());
        track.get().setDuration(dto.getDuration());
        track.get().setReleasedDate(dto.getReleasedDate());
        return TrackDTO.fromEntity(track.get());
    }

    @Override
    public TrackDTO updateDuration(Long id, Long duration) {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isEmpty())
            return null;
        track.get().setDuration(duration);
        return TrackDTO.fromEntity(track.get());
    }

    @Override
    public void delete(Long id) {
        trackRepository.deleteById(id);
    }

}
