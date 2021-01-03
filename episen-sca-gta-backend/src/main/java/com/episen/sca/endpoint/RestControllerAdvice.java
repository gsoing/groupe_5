package com.episen.sca.endpoint;

import com.episen.sca.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * il manque les exceptions que peut renvoyer spring
 */
@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler({NotFoundException.class, CannotBeModifiedException.class,
            ForbiddenException.class, LockException.class})
    public final ResponseEntity<Object> handleNotFoundException(AbstractDocumentException ex, WebRequest request) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getErrorDefinition());
    }
}
