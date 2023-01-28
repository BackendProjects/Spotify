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
        var genre3 = genreRepository.findById(3L).get();
        var genre4 = genreRepository.findById(4L).get();
        var genre5 = genreRepository.findById(5L).get();
        var genre6 = genreRepository.findById(6L).get();
        var genre7 = genreRepository.findById(7L).get();
        var genre8 = genreRepository.findById(8L).get();

        var genresForTrack1 = new ArrayList<Genre>();
        genresForTrack1.add(genre1);
        genresForTrack1.add(genre4);
        genresForTrack1.add(genre8);

        var genresForTrack2 = new ArrayList<Genre>();
        genresForTrack2.add(genre2);
        genresForTrack2.add(genre3);
        genresForTrack2.add(genre4);
        genresForTrack2.add(genre5);
        genresForTrack2.add(genre6);

        var genresForTrack3 = new ArrayList<Genre>();
        genresForTrack3.add(genre3);
        genresForTrack3.add(genre7);
        genresForTrack3.add(genre8);

        var genresForTrack4 = new ArrayList<Genre>();
        genresForTrack4.add(genre2);
        genresForTrack4.add(genre4);
        genresForTrack4.add(genre6);
        genresForTrack4.add(genre8);

        List<Track> tracks = List.of(
                new Track("track1", 400L, genresForTrack1),
                new Track("track2", 410L, genresForTrack2),
                new Track("track3", 420L, genresForTrack3),
                new Track("track4", 430L, genresForTrack4)
        );
        trackRepository.saveAll(tracks);
    }

    @Test
    void test_prueba() {
        var numTracks = trackRepository.findAll().size();
        Assertions.assertEquals(4, numTracks);
    }

    @AfterEach
    void tearDown() {
    }
}