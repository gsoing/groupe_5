package com.episen.sca.exception;

import com.episen.sca.model.ErrorDefinition;
import com.episen.sca.model.ErrorDefinitionErrors;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundException extends AbstractDocumentException{

    public static final String notFoundCode = "404";
    public static final String notFoundMessage= "The document has been not found";

    public NotFoundException() {
        super(ErrorDefinition.builder().errorType(ErrorDefinition.ErrorTypeEnum.TECHNICAL).errors(List.of(new ErrorDefinitionErrors(notFoundCode,notFoundMessage))).build(), HttpStatus.NOT_FOUND);
    }

}
