package com.mabaya.advertise.exception;

import java.util.Map;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvisor {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e,
      WebRequest request) {
    Map<String, Object> body = Map.of("status", HttpStatus.BAD_REQUEST, "message",
        "not valid due to validation error: " + e.getMessage(), "path",
        request.getDescription(false).replace("uri=", ""));
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
      WebRequest request) {
    Map<String, Object> body = Map.of("status", HttpStatus.BAD_REQUEST, "message", e.getMessage(),
        "path", request.getDescription(false).replace("uri=", ""));
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handle(Exception e, WebRequest request) {
    if (e instanceof NullPointerException | e instanceof MethodArgumentNotValidException) {
      Map<String, Object> body = Map.of("status", HttpStatus.BAD_REQUEST, "message", e.getMessage(),
          "path", request.getDescription(false).replace("uri=", ""));
      return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    Map<String, Object> body = Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR, "message",
        e.getMessage(), "path", request.getDescription(false).replace("uri=", ""));
    return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
