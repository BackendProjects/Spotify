package com.ivandavid.spotify.repository;

import com.ivandavid.spotify.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
