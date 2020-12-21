package com.episen.sca.exception;

import com.episen.sca.model.ErrorDefinition;
import com.episen.sca.model.ErrorDefinitionErrors;
import org.springframework.http.HttpStatus;

import java.util.List;

public class LockException extends AbstractDocumentException {
    public static final LockException DEFAULT = new LockException();

    private static final String CODE = "407";
    private static final String MESSAGE = "Lock already created";

    public LockException() {
        super(ErrorDefinition.builder().errorType(ErrorDefinition.ErrorTypeEnum.FUNCTIONAL).errors(List.of(new ErrorDefinitionErrors(CODE, MESSAGE))).build(),HttpStatus.BAD_REQUEST);
    }
}
