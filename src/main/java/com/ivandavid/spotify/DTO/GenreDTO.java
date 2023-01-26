package com.ivandavid.spotify.DTO;

import com.ivandavid.spotify.entity.Genre;
import com.ivandavid.spotify.entity.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {

    private Long id;
    private String name;
    private List<Long> trackIds;

    public static GenreDTO fromEntity(Genre genre) {
        var genreDTO = new GenreDTO();
        genreDTO.setName(genre.getName());
        genreDTO.setId(genre.getId());

        var trackIds = new ArrayList<Long>();
        for (Track t : genre.getTracks())
            trackIds.add(t.getId());
        genreDTO.setTrackIds(trackIds);

        return genreDTO;
    }
}
