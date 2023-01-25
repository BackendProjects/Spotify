package com.ivandavid.spotify.entity;

import com.ivandavid.spotify.DTO.GenreDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Track> tracks;

    public Genre(String name) {
        this.name = name;
    }

    public static Genre fromDTO(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        return genre;
    }
}
