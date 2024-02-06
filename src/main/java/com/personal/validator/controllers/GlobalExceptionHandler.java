package com.personal.validator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Issue> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {

        final Issue issue = new Issue();
        issue.setCode(exception.hashCode());
        issue.setMessage("Bad Request");
        issue.setErrors(getErrorsFromException(exception));

        return new ResponseEntity<>(issue, HttpStatus.BAD_REQUEST);
    }

    private List<String> getErrorsFromException(MethodArgumentNotValidException exception) {

        return exception.getFieldErrors().stream()
                .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }


}
