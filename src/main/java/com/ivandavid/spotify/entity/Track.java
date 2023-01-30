package com.ivandavid.spotify.entity;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long duration;

    @CreationTimestamp
    private LocalDateTime releasedDate;

    /*@ManyToOne(optional = false)
    private User artist;*/


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "track_genre",
            joinColumns = @JoinColumn(name = "track_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private List<Genre> genres;

    public Track(String name,
                 Long duration,
                 @NotNull List<Genre> genres/*,
                 User artist*/) {
        this.name = name;
        this.duration = duration;
        this.genres = genres;
        //this.artist = artist;
    }

    public Track(String name,
                 Long duration) {
        this.name = name;
        this.duration = duration;
        this.genres = new ArrayList<>();
        //this.artist = artist;
    }

    public static Track fromDTO(TrackDTO dto, List<Genre> genres) {
        var track = new Track();
        track.setId(dto.getId());
        track.setName(dto.getName());
        track.setDuration(dto.getDuration());
        track.setReleasedDate(dto.getReleasedDate());
        track.setGenres(genres);
        return track;
    }

}
