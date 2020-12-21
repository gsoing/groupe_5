package com.episen.sca.exception;

import com.episen.sca.model.ErrorDefinition;
import com.episen.sca.model.ErrorDefinitionErrors;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ForbiddenException extends AbstractDocumentException {
    public static final ForbiddenException DEFAULT = new ForbiddenException();
    private static final String CODE = "406";
    private static final String MESSAGE = "Forbidden Access";

    public ForbiddenException() {
        super(ErrorDefinition.builder().errorType(ErrorDefinition.ErrorTypeEnum.FUNCTIONAL).errors(List.of(new ErrorDefinitionErrors(CODE,MESSAGE))).build(), HttpStatus.BAD_REQUEST);
    }
}
