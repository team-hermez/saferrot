package com.hermez.saferrot.exception;

import com.hermez.saferrot.shipping.exception.TrackingInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TrackingInfoException.class)
    public ResponseEntity<String> handleTrackingInfoException(TrackingInfoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}