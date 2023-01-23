package com.ivandavid.spotify.repository;

import com.ivandavid.spotify.entity.Track;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TrackRepositoryTest {

    private final TrackRepository trackRepository;

    TrackRepositoryTest(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @BeforeEach
    void setUp() {
        trackRepository.deleteAll();
        List<Track> tracks = List.of(
                new Track("song1", 400L),
                new Track("song2", 410L),
                new Track("song3", 420L),
                new Track("song4", 430L),
                new Track("song5", 440L)
        );
        trackRepository.saveAll(tracks);
    }

    @Test
    void test_prueba() {
        var numTracks = trackRepository.findAll().size();
        Assertions.assertEquals(5, numTracks);
    }

    @AfterEach
    void tearDown() {
    }
}