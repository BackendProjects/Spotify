package com.ivandavid.spotify.DTO;

import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TrackDTO {

    private Long id;
    private String name;
    private Long duration;
    private LocalDateTime releasedDate;
    private List<Long> genreIds;

    public static TrackDTO fromEntity(Track track) {
        var trackDTO = new TrackDTO();
        trackDTO.setId(track.getId());
        trackDTO.setName(track.getName());
        trackDTO.setDuration(track.getDuration());
        trackDTO.setReleasedDate(track.getReleasedDate());

        var genreIds = new ArrayList<Long>();
        for (Genre g : track.getGenres())
            genreIds.add(g.getId());
        trackDTO.setGenreIds(genreIds);

        return trackDTO;
    }
}
