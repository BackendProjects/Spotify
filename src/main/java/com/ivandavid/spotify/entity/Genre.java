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
    private Long trackId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "genre_track",
            joinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id")
    )
    private List<Track> tracks;

    public Genre(String name, Long trackId) {
        this.name = name;
        this.trackId = trackId;
    }

    public static Genre fromDTO(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        genre.setId(dto.getId());
        return genre;
    }
}
