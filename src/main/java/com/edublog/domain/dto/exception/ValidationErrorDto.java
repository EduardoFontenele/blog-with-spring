package com.edublog.domain.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ValidationErrorDto {

    private HttpStatus httpStatus;
    private final Integer httpStatusCode = 400;
    Map<String, String> validationErrors;

    public ValidationErrorDto(HttpStatus httpStatus, Map<String, String> validationErrors) {
        this.httpStatus = httpStatus;
        this.validationErrors = validationErrors;
    }

}
