package com.ivandavid.spotify.repository;

import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        trackRepository.deleteAll();
        var genre1 = genreRepository.findById(1L).get();
        var genre2 = genreRepository.findById(2L).get();
        var genresForTrack1 = new ArrayList<Genre>();
        genresForTrack1.add(genre1);
        genresForTrack1.add(genre2);

        var genre3 = genreRepository.findById(3L).get();
        var genre5 = genreRepository.findById(5L).get();
        var genresForTrack2 = new ArrayList<Genre>();
        genresForTrack2.add(genre3);
        genresForTrack2.add(genre5);

        var genre6 = genreRepository.findById(7L).get();
        var genre7 = genreRepository.findById(9L).get();
        var genresForTrack3 = new ArrayList<Genre>();
        genresForTrack2.add(genre6);
        genresForTrack2.add(genre7);

        List<Track> tracks = List.of(
                new Track("song1", 400L, genresForTrack1),
                new Track("song2", 410L, genresForTrack2),
                new Track("song3", 410L, genresForTrack3)
        );
        trackRepository.saveAll(tracks);
    }

    @Test
    void test_prueba() {
        var numTracks = trackRepository.findAll().size();
        Assertions.assertEquals(3, numTracks);
    }

    @AfterEach
    void tearDown() {
    }
}