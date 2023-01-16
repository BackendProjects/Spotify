package com.ivandavid.spotify.entity;

import com.ivandavid.spotify.DTO.GenreDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.midi.Track;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    /*@JoinTable(
            name = "genre_track",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )*/
    private List<Track> tracks;

    public Genre(String name) {
        this.name = name;
    }

    public static Genre fromDTO(GenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.getName());
        genre.setId(dto.getId());
        return genre;
    }
}
