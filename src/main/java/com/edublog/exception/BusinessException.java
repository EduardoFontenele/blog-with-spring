package com.edublog.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@ToString
public class BusinessException extends RuntimeException {

    private final Integer httpStatusCode;
    private final HttpStatus httpStatus;
    private final String message;


    public BusinessException(String message, HttpStatus httpStatus, Integer httpStatusCode) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public BusinessException(ExceptionsTemplate exceptionsTemplate, String message) {
        super();
        this.httpStatus = exceptionsTemplate.getHttpStatus();
        this.message = message;
        this.httpStatusCode = exceptionsTemplate.getHttpStatusCode();
    }

    public BusinessException(ExceptionsTemplate exceptionsTemplate) {
        super();
        this.httpStatus = exceptionsTemplate.getHttpStatus();
        this.message = exceptionsTemplate.getMessage();
        this.httpStatusCode = exceptionsTemplate.getHttpStatusCode();
    }
}
