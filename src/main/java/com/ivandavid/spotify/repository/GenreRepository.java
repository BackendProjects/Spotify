package com.ivandavid.spotify.repository;

import com.ivandavid.spotify.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Boolean existsGenreByName(@NotBlank String name);

}
