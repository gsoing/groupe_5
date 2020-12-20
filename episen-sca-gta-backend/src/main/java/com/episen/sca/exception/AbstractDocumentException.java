package com.episen.sca.exception;

import com.episen.sca.model.ErrorDefinition;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AbstractDocumentException extends  RuntimeException{

    private final ErrorDefinition errorDefinition;
    private final HttpStatus httpStatus;

    public AbstractDocumentException(ErrorDefinition errorDefinition, HttpStatus httpStatus) {
        this.errorDefinition = errorDefinition;
        this.httpStatus = httpStatus;
    }
}
