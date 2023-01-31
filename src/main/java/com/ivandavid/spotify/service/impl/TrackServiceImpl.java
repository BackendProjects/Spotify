package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.enums.EntityName;
import com.ivandavid.spotify.enums.SearchParamType;
import com.ivandavid.spotify.exception.ResourceNotFoundException;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.repository.TrackRepository;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ivandavid.spotify.enums.EntityName.TRACK;
import static com.ivandavid.spotify.enums.SearchParamType.ID;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final GenreRepository genreRepository;

    public TrackServiceImpl(TrackRepository trackRepository, GenreRepository genreRepository) {
        this.trackRepository = trackRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public TrackDTO createTrack(TrackDTO dto) {
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
    public List<TrackDTO> getAllTracks() {
        var tracks = trackRepository.findAll();
        if (tracks.isEmpty())
            throw new ResourceNotFoundException(TRACK, ID);
        var trackDTOS = new ArrayList<TrackDTO>();
        for (Track track : tracks)
            trackDTOS.add(TrackDTO.fromEntity(track));
        return trackDTOS;
    }

    @Override
    public TrackDTO getTrackById(Long id) {
        var track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TRACK, ID, id));
        return TrackDTO.fromEntity(track);
    }

    @Override
    public TrackDTO updateTrack(Long id, TrackDTO dto) {
        var genres = dto.getGenreIds().stream().map(genreId -> genreRepository.findById(genreId).get()).toList();
        dto.setId(id);
        var track = new Track();
        track.setId(id);
        track.setName(dto.getName());
        track.setDuration(dto.getDuration());
        track.setReleasedDate(dto.getReleasedDate());
        track.setGenres(genres);
        var storedTrack = trackRepository.save(track);
        return TrackDTO.fromEntity(storedTrack);
    }

    @Override
    public TrackDTO updateDuration(Long id, Long duration) {
        var track = getTrackEntityById(id);
        track.setDuration(duration);
        var storedTrack = trackRepository.save(track);
        return TrackDTO.fromEntity(storedTrack);
    }

    @Override
    public void deleteTrack(Long id) {
        var track = getTrackEntityById(id);
        trackRepository.delete(track);
    }

    public Track getTrackEntityById(Long id) {
        return trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TRACK, ID, id));
    }

    @Override
    public List<Track> findTracksByGenre(Genre genre) {
        return trackRepository.findTracksByGenre(genre);
    }

}
