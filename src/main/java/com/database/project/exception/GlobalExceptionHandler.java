package com.database.project.exception;

import com.database.project.exception.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handlerNotFoundException(NotFoundException notFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setId(notFoundException.getId());
        errorDTO.setMessage(notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);

    }
}
