package com.ivandavid.spotify.repository;

import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    @Query("SELECT t FROM Track t WHERE ?1 MEMBER OF t.genres")
    List<Track> getTracksByGenre(Genre genre);
}
