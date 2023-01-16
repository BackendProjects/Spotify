package com.ivandavid.spotify.service.impl;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.repository.GenreRepository;
import com.ivandavid.spotify.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreDTO create(GenreDTO dto) {
        Genre genre = new Genre(dto.getName());
        Genre storedGenre = genreRepository.save(genre);
        return GenreDTO.fromEntity(storedGenre);
    }

    @Override
    public List<GenreDTO> findAll() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDTO> genresDTO = new ArrayList<>();
        for (Genre g : genres)
            genresDTO.add(GenreDTO.fromEntity(g));
        return genresDTO;
    }

    @Override
    public GenreDTO findById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty())
            return null;
        return GenreDTO.fromEntity(genre.get());
    }

    @Override
    public GenreDTO updateById(Long id, GenreDTO dto) {
        Genre genre = genreRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Genre id " + id + " not valid!"));
        genre.setName(dto.getName());
        genreRepository.save(genre);
        return GenreDTO.fromEntity(genre);
    }

    @Override
    public GenreDTO updateNameById(Long id, String name) {
        Genre genre = genreRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Genre id " + id + " not valid!"));
        genre.setName(name);
        genreRepository.save(genre);
        return GenreDTO.fromEntity(genre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

}
