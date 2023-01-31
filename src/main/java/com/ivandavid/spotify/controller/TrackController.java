package com.ivandavid.spotify.controller;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<TrackDTO> create(@RequestBody TrackDTO dto) {
        var trackDTO = trackService.create(dto);
        return new ResponseEntity<>(trackDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrackDTO>> findAll() {
        var trackDTOS = trackService.findAll();
        return ResponseEntity.ok(trackDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDTO> findById(@PathVariable Long id) {
        var trackDTO = trackService.findById(id);
        return ResponseEntity.ok(trackDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackDTO> update(@PathVariable Long id, @RequestBody TrackDTO dto) {
        var trackDTO = trackService.update(id, dto);
        return ResponseEntity.ok(trackDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrackDTO> updateDuration(@PathVariable Long id, @RequestParam Long duration) {
        var trackDTO = trackService.updateDuration(id, duration);
        return ResponseEntity.ok(trackDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trackService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
