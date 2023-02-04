package com.ivandavid.spotify.enums;

public enum ExceptionMessage {
    NOT_INPUT_ELEMENT("There is no input element. Check the Object attributes required"),
    NAME_ALREADY_TAKEN("name is already taken");

    public final String value;
    ExceptionMessage(String value) {
        this.value = value;
    }
}
