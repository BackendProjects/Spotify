package com.ivandavid.spotify.entity;

import com.ivandavid.spotify.DTO.TrackDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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

    @ManyToMany(mappedBy = "tracks")
    private List<Genre> genres;

    public Track(String name,
                 Long duration/*,
                 User artist*/) {
        this.name = name;
        this.duration = duration;
        //this.artist = artist;
    }

    public static Track fromDTO(TrackDTO dto) {
        Track track = new Track();
        track.setName(dto.getName());
        track.setDuration(dto.getDuration());
        track.setReleasedDate(dto.getReleasedDate());
        return track;
    }

}
