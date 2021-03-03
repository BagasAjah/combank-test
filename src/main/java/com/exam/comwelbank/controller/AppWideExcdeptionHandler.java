package com.exam.comwelbank.controller;

import com.exam.comwelbank.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExcdeptionHandler {

    @ExceptionHandler(CustomException.class)
    public String showErrorFromService(CustomException exception) {
        return exception.getMessage();
    }
}