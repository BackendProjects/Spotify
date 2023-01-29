package com.ivandavid.spotify.exception;

public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(Long id) {
        super("Track id " + id + " is invalid!");
    }
}
