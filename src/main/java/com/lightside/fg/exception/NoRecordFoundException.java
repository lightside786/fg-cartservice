package com.lightside.fg.exception;

import com.lightside.fg.web.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Anwar
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRecordFoundException extends ApplicationException {

    public NoRecordFoundException(final String errorKey, final String errorMessageKey) {
        super(errorKey, errorMessageKey);
    }
    public NoRecordFoundException(ErrorResponse error) {
        super(error);
    }

}
