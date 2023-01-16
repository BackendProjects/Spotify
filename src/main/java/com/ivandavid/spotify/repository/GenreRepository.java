package com.ivandavid.spotify.repository;

import com.ivandavid.spotify.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
