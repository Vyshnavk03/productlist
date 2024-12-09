package com.vyshnav.product.exception;

import com.vyshnav.product.dto.CategoryDTO;
import com.vyshnav.product.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex,
                                                                       WebRequest webRequest){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                webRequest.getDescription(false),// api path will get here in this
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDTO);
    }


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException ex,
                                                                                WebRequest webRequest){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                webRequest.getDescription(false),// api path will get here in this
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleCategoryNotFoundException(Exception ex,
                                                                                WebRequest webRequest){

        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                webRequest.getDescription(false),// api path will get here in this
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
    }
}
