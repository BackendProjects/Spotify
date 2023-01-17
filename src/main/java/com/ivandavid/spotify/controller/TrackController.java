package com.ivandavid.spotify.controller;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tracks")
public class TrackController {

    private final TrackService trackService;

    @PostMapping
    public ResponseEntity<TrackDTO> create(@RequestBody TrackDTO dto) {
        TrackDTO trackDTO = trackService.create(dto);
        return new ResponseEntity<>(trackDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrackDTO>> findAll() {
        return ResponseEntity.ok(trackService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDTO> findById(@PathVariable Long id) {
        TrackDTO trackDTO = trackService.findById(id);
        if (trackDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(trackDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackDTO> update(@PathVariable Long id, @RequestBody TrackDTO dto) {
        TrackDTO trackDTO = trackService.update(id, dto);
        if (trackDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(trackDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrackDTO> updateDuration(@PathVariable Long id, @RequestParam Long duration) {
        TrackDTO trackDTO = trackService.updateDuration(id, duration);
        if (trackDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(trackDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        TrackDTO trackDTO = trackService.findById(id);
        if (trackDTO != null) {
            trackService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
