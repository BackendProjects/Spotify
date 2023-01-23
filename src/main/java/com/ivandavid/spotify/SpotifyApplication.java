package com.ivandavid.spotify;

import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.repository.TrackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpotifyApplication implements CommandLineRunner {

    private final TrackRepository trackRepository;
    private final GenreRepository genreRepository;

    public SpotifyApplication(TrackRepository trackRepository, GenreRepository genreRepository) {
        this.trackRepository = trackRepository;
        this.genreRepository = genreRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpotifyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        trackRepository.deleteAll();
        var tracks = List.of(
                new Track("song1", 400L),
                new Track("song2", 410L),
                new Track("song3", 420L)
        );
        trackRepository.saveAll(tracks);

        var genre = new Genre("genre1", 2L);
        genreRepository.save(genre);

    }
}
