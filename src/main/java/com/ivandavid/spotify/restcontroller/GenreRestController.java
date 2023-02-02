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
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO dto) {
        var genreDTO = genreService.create(dto);
        return new ResponseEntity<>(genreDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAll() {
        var genreDTOS = genreService.findAll();
        if (genreDTOS == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(genreDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
        var genreDTO =  genreService.findById(id);
        return ResponseEntity.ok(genreDTO);
    }

    @GetMapping("/{id}/tracks")
    public ResponseEntity<List<TrackDTO>> findTracksById(@PathVariable Long id) {
        var TracksDTOs = genreService.findTracksByGenreId(id);
        /*if (TracksDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
        return ResponseEntity.ok(TracksDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO dto) {
        return ResponseEntity.ok((genreService.update(id, dto)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenreDTO> updateName(@PathVariable Long id, @RequestParam String name) {
        return ResponseEntity.ok(genreService.updateName(id, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        var genreDTO = genreService.findById(id);
        if (genreDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       genreService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
