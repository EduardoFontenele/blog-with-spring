package com.edublog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ExceptionsTemplate {

    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User with username '[item]' already exists", 409),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid operation. Please, try again", 400);

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer httpStatusCode;
}
