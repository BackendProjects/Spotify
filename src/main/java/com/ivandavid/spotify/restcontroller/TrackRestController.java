package com.ivandavid.spotify.restcontroller;

import com.ivandavid.spotify.DTO.TrackDTO;
import com.ivandavid.spotify.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tracks")
public class TrackRestController {

    private final TrackService trackService;

    public TrackRestController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<TrackDTO> createTrack(@RequestBody TrackDTO dto) {
        var trackDTO = trackService.createTrack(dto);
        return new ResponseEntity<>(trackDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TrackDTO>> getAllTracks() {
        var trackDTOS = trackService.getAllTracks();
        return ResponseEntity.ok(trackDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDTO> getTrackById(@PathVariable Long id) {
        var trackDTO = trackService.getTrackById(id);
        return ResponseEntity.ok(trackDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackDTO> updateTrack(@PathVariable Long id, @RequestBody TrackDTO dto) {
        var trackDTO = trackService.updateTrack(id, dto);
        return ResponseEntity.ok(trackDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TrackDTO> updateDuration(@PathVariable Long id, @RequestParam Long duration) {
        var trackDTO = trackService.updateDuration(id, duration);
        return ResponseEntity.ok(trackDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrackById(@PathVariable Long id) {
        trackService.deleteTrackById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
