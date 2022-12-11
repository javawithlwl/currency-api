package com.lwl.currency.advice;

import com.lwl.currency.dto.ApiErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value
      = {IllegalArgumentException.class, IllegalStateException.class})

  protected ResponseEntity<ApiErrorMessage> handleConflict(
      RuntimeException ex, WebRequest request) {
    ApiErrorMessage apiErrorMessage = ApiErrorMessage.builder().message(ex.getMessage())
        .httpStatus(HttpStatus.BAD_REQUEST)
        .localDateTime(LocalDateTime.now())
        .build();
    return ResponseEntity.badRequest().body(apiErrorMessage);
  }
}
