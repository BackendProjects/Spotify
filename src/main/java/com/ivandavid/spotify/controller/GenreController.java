package com.ivandavid.spotify.controller;

import com.ivandavid.spotify.DTO.GenreDTO;
import com.ivandavid.spotify.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO dto) {
        GenreDTO genreDTO = genreService.create(dto);
        return new ResponseEntity<>(genreDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAll() {
        return ResponseEntity.ok(genreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> findById(@PathVariable Long id) {
        GenreDTO genreDTO = genreService.findById(id);
        if (genreDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(genreService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateById(@PathVariable Long id, @RequestBody GenreDTO dto) {
        return ResponseEntity.ok((genreService.updateById(id, dto)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GenreDTO> updateNameById(@PathVariable Long id, @RequestParam String name) {
        return ResponseEntity.ok(genreService.updateNameById(id, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        GenreDTO genreDTO = genreService.findById(id);
        if (genreDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       genreService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
