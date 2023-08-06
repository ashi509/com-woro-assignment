package com.woro.assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {
   @ExceptionHandler(CustomException.class)
    ResponseEntity<?> handleCustomException(CustomException customException){
       log.error(customException.getErrorMessage());
       return ResponseEntity.status(customException.getStatusCode()).body(customException.getErrorMessage());

   }

}
