package com.task.StudentMS.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)    //Catch exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)                     //Catch Bad request
    @ResponseBody                                               //Send response body
    String handleConstraintViolationException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getAllErrors().get(0).getDefaultMessage();      //Gets the message issued
    }

    @ExceptionHandler(ConstraintViolationException.class)    //Catch exception
    @ResponseStatus(HttpStatus.BAD_REQUEST)                     //Catch Bad request
    @ResponseBody                                               //Send response body
    String handleConstraintViolationException(ConstraintViolationException e) {
        return e.getConstraintViolations().iterator().next().getMessage();      //Gets the message issued
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String handleIdNotPresent(NoSuchElementException e){
        return "The ID is not present in database.";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    String handleWrongMethodUse(HttpRequestMethodNotSupportedException e){
        return "This method is not allowed.";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    String handleWrongArgument(IllegalArgumentException e){
        return "This argument is not accepted.";
    }

}
