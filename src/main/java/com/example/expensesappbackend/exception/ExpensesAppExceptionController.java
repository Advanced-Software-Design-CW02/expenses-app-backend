package com.example.expensesappbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExpensesAppExceptionController {
    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> exception(DataNotFoundException exception) {
        return new ResponseEntity<>("Data not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataNotCreateException.class)
    public ResponseEntity<Object> exception(DataNotCreateException exception) {
        return new ResponseEntity<>("Data not create", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
