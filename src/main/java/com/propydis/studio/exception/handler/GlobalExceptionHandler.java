package com.propydis.studio.exception.handler;

import com.propydis.studio.exception.ApiError;
import com.propydis.studio.exception.exceptions.NotFoundByIdException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotFoundByIdException.class)
    public ResponseEntity<ApiError> handleNotFoundByIdException(NotFoundByIdException e, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "No encontrado",
                e.getMessage(),
                request.getRequestURI()

        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


}
