package com.matheusncodello.applicationws.controllers;

import com.matheusncodello.applicationws.infra.configurations.exception.UserExistsException;
import com.matheusncodello.applicationws.infra.configurations.exception.Errors;
import com.matheusncodello.applicationws.infra.configurations.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
