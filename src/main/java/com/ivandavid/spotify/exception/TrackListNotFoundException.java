package com.ivandavid.spotify.exception;

public class TrackListNotFoundException extends RuntimeException{

    public TrackListNotFoundException() {
        super("There are no tracks in DB");
    }
}
