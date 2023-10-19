package com.edublog.exception;

import com.edublog.domain.dto.exception.BusinessExceptionDto;
import com.edublog.domain.dto.exception.ValidationErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionDto> handle(BusinessException ex) {
        BusinessExceptionDto businessExceptionDto = new BusinessExceptionDto(ex.getHttpStatus(), ex.getMessage(), ex.getHttpStatusCode());
        return ResponseEntity.status(ex.getHttpStatus()).body(businessExceptionDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDto> handle(MethodArgumentNotValidException ex) {
        ValidationErrorDto dto = new ValidationErrorDto();
        Map<String, String> validationErrors = new HashMap<>();

        for(FieldError fieldError : ex.getFieldErrors()) {
            String field = fieldError.getField();
            String error = fieldError.getDefaultMessage();

            validationErrors.put(field, error);
        }

        dto.setHttpStatus(HttpStatus.BAD_REQUEST);
        dto.setValidationErrors(validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
}
