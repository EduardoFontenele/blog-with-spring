package com.edublog.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public BusinessException(ExceptionsTemplate exceptionsTemplate) {
        super();
        this.httpStatusCode = exceptionsTemplate.getHttpStatusCode();
        this.httpStatus = exceptionsTemplate.getHttpStatus();
        this.message = exceptionsTemplate.getMessage();
    }


    public BusinessException(ExceptionsTemplate exceptionsTemplate, String item) {
        super();
        this.httpStatusCode = exceptionsTemplate.getHttpStatusCode();
        this.httpStatus = exceptionsTemplate.getHttpStatus();
        this.message = exceptionsTemplate.getMessage().replace("[item]", item);
    }

    public BusinessException(ExceptionsTemplate exceptionsTemplate, String item, String message) {
        super();
        this.httpStatusCode = exceptionsTemplate.getHttpStatusCode();
        this.httpStatus = exceptionsTemplate.getHttpStatus();
        this.message = message;
    }
}
