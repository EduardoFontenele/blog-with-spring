package com.edublog.domain.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessExceptionDto {
    private HttpStatus httpStatus;
    private String message;
    private Integer httpStatusCode;
}
