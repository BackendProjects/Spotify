package com.ivandavid.spotify.DTO;

import com.ivandavid.spotify.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {

    private Long id;
    private String name;

    public static GenreDTO fromEntity(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(genre.getName());
        genreDTO.setId(genre.getId());
        return genreDTO;
    }
}
