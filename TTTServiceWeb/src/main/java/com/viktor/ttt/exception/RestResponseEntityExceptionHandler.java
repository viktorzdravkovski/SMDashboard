package com.viktor.ttt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler advice around the REST controllers.
 */
@ControllerAdvice(annotations = {RestController.class})
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Exception handler for when the username already exists.
   *
   * @param exception the {@link UsernameAlreadyExistsException}.
   * @param request the {@link WebRequest}.
   * @return the response entity.
   */
  @ExceptionHandler(value = {UsernameAlreadyExistsException.class})
  protected ResponseEntity<Object> handleConflict(UsernameAlreadyExistsException exception, WebRequest request) {
    String body = "Username already exists.";
    return handleExceptionInternal(exception, body, new HttpHeaders(), HttpStatus.PRECONDITION_FAILED, request);
  }
}
