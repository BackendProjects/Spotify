package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.exception.ResourceNotFoundException;
import com.ivandavid.spotify.repository.TrackRepository;
import com.ivandavid.spotify.service.GenreService;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ivandavid.spotify.enums.EntityName.TRACK;
import static com.ivandavid.spotify.enums.SearchParamType.ID;

@Component
@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;
    private final GenreService genreService;

    public TrackServiceImpl(TrackRepository trackRepository, @Lazy GenreService genreService) {
        this.trackRepository = trackRepository;
        this.genreService = genreService;
    }

    @Override
    public TrackDTO createTrack(TrackDTO dto) {
        var genres = dto.getGenreIds().stream().map(genreService::getGenreEntityById).toList();
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
        return tracks.stream().map(TrackDTO::fromEntity).toList();
    }

    @Override
    public TrackDTO getTrackById(Long id) {
        var track = trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TRACK, ID, id));
        return TrackDTO.fromEntity(track);
    }

    @Override
    public Track getTrackEntityById(Long id) {
        return trackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(TRACK, ID, id));
    }

    @Override
    public TrackDTO updateTrack(Long id, TrackDTO dto) {
        var genres = dto.getGenreIds().stream().map(genreService::getGenreEntityById).toList();
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
    public void deleteTrackById(Long id) {
        var track = getTrackEntityById(id);
        trackRepository.delete(track);
    }

    @Override
    public List<Track> getTracksByGenre(Genre genre) {
        return trackRepository.getTracksByGenre(genre);
    }

}
