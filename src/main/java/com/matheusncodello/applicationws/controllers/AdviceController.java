package com.matheusncodello.applicationws.controllers;

import com.matheusncodello.applicationws.controllers.dto.DefaultExceptionResponse;
import com.matheusncodello.applicationws.controllers.dto.DefaultResponseEnvelope;
import com.matheusncodello.applicationws.infra.configurations.exception.Errors;
import com.matheusncodello.applicationws.infra.configurations.exception.NotFoundException;
import com.matheusncodello.applicationws.infra.configurations.exception.UserExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AdviceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceController.class);

    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleUserExists(UserExistsException e){
        return new Errors(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors handleUserFound(NotFoundException e){
        return new Errors(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<DefaultResponseEnvelope<DefaultExceptionResponse>> handleBusinessException(
            HttpServletRequest request, Exception exception, HttpServletResponse response
    ){
        LOGGER.info(
                "[ADVICER] Tratando uma exceção não mapeada: {} - URL: [{}]",
                exception.getMessage(),
                request.getRequestURL()
        );

        return ResponseEntity
                .status(512)
                .body(new DefaultResponseEnvelope<>(
                        false,
                        new DefaultExceptionResponse(
                                exception.getMessage(),
                                request.getRequestURI()
                        )
                ));
    }
}
