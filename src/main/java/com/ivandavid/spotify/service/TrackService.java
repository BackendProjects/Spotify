package com.ivandavid.spotify.service;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;

import java.util.List;

public interface TrackService {
    TrackDTO createTrack(TrackDTO dto);

    List<TrackDTO> getAllTracks();

    TrackDTO getTrackById(Long id);

    TrackDTO updateDuration(Long id, Long duration);

    TrackDTO updateTrack(Long id, TrackDTO dto);

    void deleteTrack(Long id);

    List<Track> findTracksByGenre(Genre genre);
}
