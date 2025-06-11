package com.api.notifications.push.controller;

import com.api.notifications.push.services.ErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ErrorService.class)
    public ResponseEntity<String> manejarErrorService(ErrorService es) {
        String message = es.getMessage();
        if(message.contains("Token de dispositivo inválido")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErrorDesconocido(Exception ex) {
        // Excepciones a ignorar para que tome Swagger
        if (ex instanceof org.springframework.web.servlet.NoHandlerFoundException
                || ex instanceof org.springframework.web.HttpRequestMethodNotSupportedException
                || ex instanceof org.springframework.web.bind.MissingServletRequestParameterException) {
            throw (RuntimeException) ex; // dejás que Spring las maneje
        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + ex.getMessage());
    }
}
