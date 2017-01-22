package com.lightside.fg.exception;

import com.lightside.fg.web.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

/**
 * @author Anwar
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreateCartException extends ApplicationException {

    public CreateCartException(String errorKey, String messageKey) {
        super(errorKey, messageKey);
    }

    public CreateCartException(Collection<ErrorResponse> errors) {
        super(errors);
    }
}
