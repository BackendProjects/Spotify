package com.ivandavid.spotify.exception;

import com.ivandavid.spotify.exception.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler({GenreNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorMessage notFoundHandler(Exception ex) {
        return new ErrorMessage(ex.getMessage());
    }

    /*@ResponseBody
    @ExceptionHandler({GenreNotFoundExeption.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundHandler(Exception ex) {
        return ex.getMessage();
    }*/
}

/*GenreNotFoundException.class,
            TrackNotFoundException.class,
            UserNotFoundException.class,
            PlaylistNotFoundException.class,
            RoleNotFoundException.class,
            LikeTrackNotFoundException.class,
            NoLyricsFoundException.class*/