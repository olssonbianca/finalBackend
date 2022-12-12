package com.example.demo.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalException extends Exception{
    private static final Logger logger = Logger.getLogger(GlobalException.class);
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> datosIncorrectos(BadRequestException e){
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> noExiste(NotFoundException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
