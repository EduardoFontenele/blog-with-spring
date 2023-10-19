package com.edublog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@RequiredArgsConstructor
@Getter
public enum ExceptionsTemplate {

    RESOURCE_ALREADY_EXISTS(HttpStatus.CONFLICT, "Resource already exists", 409),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid resource, please, try again", 400);

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer httpStatusCode;
}
