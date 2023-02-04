package com.ivandavid.spotify.enums;

public enum EntityName {
    TRACK("Track"),
    GENRE("Genre");

    public final String value;

    EntityName(String value) {
        this.value = value;
    }
}
