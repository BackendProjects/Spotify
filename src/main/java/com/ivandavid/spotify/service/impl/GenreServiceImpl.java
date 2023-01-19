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
        if (genres.isEmpty())
            return null;
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
    public GenreDTO update(Long id, GenreDTO dto) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty())
            return null;
        genre.get().setName(dto.getName());
        return GenreDTO.fromEntity(genre.get());
    }

    @Override
    public GenreDTO updateName(Long id, String name) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty())
            return null;
        genre.get().setName(name);
        return GenreDTO.fromEntity(genre.get());
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

}
