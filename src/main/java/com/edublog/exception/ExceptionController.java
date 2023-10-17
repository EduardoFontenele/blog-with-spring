package com.edublog.exception;

import com.edublog.domain.dto.exception.BusinessExceptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionDto> handle(BusinessException ex) {
        BusinessExceptionDto businessExceptionDto = new BusinessExceptionDto(ex.getHttpStatus(), ex.getMessage(), ex.getHttpStatusCode());
        return ResponseEntity.status(ex.getHttpStatus()).body(businessExceptionDto);
    }

}
