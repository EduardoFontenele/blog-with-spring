package com.edublog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;



@Getter
public enum ExceptionsTemplate {
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "User with username '[item]' already exists", 409),
    USER_IS_BANNED(HttpStatus.UNAUTHORIZED, "User is blocked from system", 401),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found", 404),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid operation. Please, try again", 400);

    private final HttpStatus httpStatus;
    private final String message;
    private final Integer httpStatusCode;

    ExceptionsTemplate(HttpStatus httpStatus, String message, Integer httpStatusCode) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
