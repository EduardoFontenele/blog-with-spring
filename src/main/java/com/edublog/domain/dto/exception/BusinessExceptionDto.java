package com.edublog.domain.dto.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"httpStatus", "httpStatusCode", "message"})
public class BusinessExceptionDto {
    private HttpStatus httpStatus;
    private String message;
    private Integer httpStatusCode;
}
