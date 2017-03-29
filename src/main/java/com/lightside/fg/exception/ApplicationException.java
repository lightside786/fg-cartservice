package com.lightside.fg.exception;

import com.lightside.fg.web.response.ErrorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;

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
    private  Object[] params;

    public ApplicationException(String errorKey, String messageKey) {
        this.errorKey = errorKey;
        this.messageKey = messageKey;
    }

    public ApplicationException(String errorKey, String messageKey, Object[] params) {
        this.errorKey = errorKey;
        this.messageKey = messageKey;
        this.params = params;
    }

    public ApplicationException(String errorKey) {
        this.errorKey = errorKey;
    }

    public ApplicationException(Collection<ErrorResponse> errors) {
        this.errors = errors;
    }


    public ApplicationException(ErrorResponse error) {
        if(this.errors == null){
            this.errors = new HashSet<ErrorResponse>();
        }
        this.errors.add(error);
    }


    public ApplicationException(ErrorResponse error , Object[] params) {
        if(this.errors == null){
            this.errors = new HashSet<ErrorResponse>();
        }
        this.errors.add(error);
        this.params = params;
    }
}
