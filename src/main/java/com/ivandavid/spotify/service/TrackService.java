package com.ivandavid.spotify.service;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;

import java.util.List;

public interface TrackService {
    TrackDTO create(TrackDTO dto);

    List<TrackDTO> findAll();

    TrackDTO findById(Long id);

    TrackDTO updateDuration(Long id, Long duration);

    TrackDTO update(Long id, TrackDTO dto);

    void delete(Long id);

    List<Track> findTracksByGenre(Genre genre);
}
