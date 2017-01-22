package com.lightside.fg.exception;

import com.lightside.fg.web.response.ErrorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * @author Anwar
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    private Collection<ErrorResponse> errors;
    private String errorKey;
    private String messageKey;

    public ApplicationException(String errorKey, String messageKey) {
        this.errorKey = errorKey;
        this.messageKey = messageKey;
    }

    public ApplicationException(String errorKey) {
        this.errorKey = errorKey;
    }

    public ApplicationException(Collection<ErrorResponse> errors) {
        this.errors = errors;
    }
}
