package com.ivandavid.spotify.restcontroller;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreRestController {

    private final GenreService genreService;

    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO dto) {
        var genreDTO = genreService.createGenre(dto);
        return new ResponseEntity<>(genreDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres() {
        var genreDTOS = genreService.getAllGenres();
        return ResponseEntity.ok(genreDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        var genreDTO =  genreService.getGenreById(id);
        return ResponseEntity.ok(genreDTO);
    }

    @GetMapping("/{id}/tracks")
    public ResponseEntity<List<TrackDTO>> GetTracksByGenreId(@PathVariable Long id) {
        var TracksDTOs = genreService.GetTracksByGenreId(id);
        return ResponseEntity.ok(TracksDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(@PathVariable Long id, @RequestBody GenreDTO dto) {
        return ResponseEntity.ok((genreService.updateGenre(id, dto)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenreDTO> updateName(@PathVariable Long id, @RequestParam String name) {
        return ResponseEntity.ok(genreService.updateName(id, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable Long id) {
       genreService.deleteGenreById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
