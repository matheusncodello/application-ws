package com.sptech.applicationws.controllers;

import com.sptech.applicationws.infra.configurations.exception.Errors;
import com.sptech.applicationws.infra.configurations.exception.NotFoundException;
import com.sptech.applicationws.infra.configurations.exception.UserExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

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
}
