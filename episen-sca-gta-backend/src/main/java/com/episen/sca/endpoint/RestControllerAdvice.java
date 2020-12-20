package com.episen.sca.endpoint;

import com.episen.sca.exception.AbstractDocumentException;
import com.episen.sca.exception.CannotBeModifiedException;
import com.episen.sca.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({NotFoundException.class, CannotBeModifiedException.class})public final ResponseEntity<Object> handleNotFoundException(AbstractDocumentException ex, WebRequest request) {return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErrorDefinition());}
}
