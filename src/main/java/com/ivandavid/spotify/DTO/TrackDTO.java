package com.ivandavid.spotify.DTO;

import com.ivandavid.spotify.entity.Track;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TrackDTO {

    private Long id;
    private String name;
    private Long duration;
    private LocalDateTime releasedDate;

    public static TrackDTO fromEntity(Track track) {
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(track.getId());
        trackDTO.setName(track.getName());
        trackDTO.setDuration(track.getDuration());
        trackDTO.setReleasedDate(track.getReleasedDate());
        return trackDTO;
    }
}
