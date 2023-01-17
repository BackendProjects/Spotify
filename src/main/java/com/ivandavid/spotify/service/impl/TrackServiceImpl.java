package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.repository.TrackRepository;
import com.ivandavid.spotify.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Override
    public TrackDTO create(TrackDTO dto) {
        Track track = new Track(
                dto.getName(),
                dto.getDuration()
        );
        Track storedTrack = trackRepository.save(track);
        return TrackDTO.fromEntity(storedTrack);
    }

    @Override
    public List<TrackDTO> findAll() {
        List<Track> tracks = trackRepository.findAll();
        List<TrackDTO> trackDTOS = new ArrayList<>();
        for (Track track : tracks)
            trackDTOS.add(TrackDTO.fromEntity(track));
        return trackDTOS;
    }

    @Override
    public TrackDTO findById(Long id) {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isEmpty()) return null;
        return TrackDTO.fromEntity(track.get());
    }

    @Override
    public TrackDTO update(Long id, TrackDTO dto) {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isPresent()) {
            track.get().setName(dto.getName());
            track.get().setDuration(dto.getDuration());
            track.get().setReleasedDate(dto.getReleasedDate());
            return TrackDTO.fromEntity(track.get());
        }
        return null;
    }

    @Override
    public TrackDTO updateDuration(Long id, Long duration) {
        Optional<Track> track = trackRepository.findById(id);
        if (track.isPresent()) {
            track.get().setDuration(duration);
            return TrackDTO.fromEntity(track.get());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        trackRepository.deleteById(id);
    }


}
