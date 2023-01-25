package com.ivandavid.spotify.repository;

import com.ivandavid.spotify.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void setUp() {
        genreRepository.deleteAll();
        var genres = List.of(
                new Genre("Genre1"),
                new Genre("Genre2"),
                new Genre("Genre3"),
                new Genre("Genre4"),
                new Genre("Genre5"),
                new Genre("Genre6"),
                new Genre("Genre7"),
                new Genre("Genre8"),
                new Genre("Genre9"),
                new Genre("Genre10")
        );
        genreRepository.saveAll(genres);
    }

    @Test
    void test_prueba() {
        var numGenres = genreRepository.findAll().size();
        Assertions.assertEquals(10, numGenres);
    }

    @AfterEach
    void tearDown() {
    }
}