package com.ivandavid.spotify.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Genre id " + id + " is invalid!");
    }
}
