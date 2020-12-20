package com.episen.sca.exception;

import com.episen.sca.model.ErrorDefinition;
import com.episen.sca.model.ErrorDefinitionErrors;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CannotBeModifiedException extends AbstractDocumentException{

    public final static String cannotBeModifiedCode = "405";
    public final static String cannotBeModifiedMessage= "The document can't be modified";


    public CannotBeModifiedException() {
        super(ErrorDefinition.builder().errorType(ErrorDefinition.ErrorTypeEnum.TECHNICAL).errors(List.of(new ErrorDefinitionErrors(cannotBeModifiedCode,cannotBeModifiedMessage))).build(), HttpStatus.NOT_FOUND);
    }

}
