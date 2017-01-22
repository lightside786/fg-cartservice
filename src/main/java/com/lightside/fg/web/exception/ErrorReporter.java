package com.lightside.fg.web.exception;

import com.lightside.fg.exception.CreateCartException;
import com.lightside.fg.web.locale.MessageResolver;
import com.lightside.fg.web.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Anwar
 */

@Component
@Slf4j
public class ErrorReporter {

    private MessageResolver messageResolver;

    public ErrorReporter(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public List<ErrorResponse> createErrors(final BindingResult bindingResult) {
        final List<ErrorResponse> errors = new ArrayList<>(bindingResult.getFieldErrorCount());
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            //TODO Anwar find ways to optimize this
            String errorKey = fieldError.getDefaultMessage() + ".key";
            String errorMessageKey = fieldError.getDefaultMessage() + ".message";
            errors.add(ErrorResponse.buildError(messageResolver.resolveMessage(errorKey), messageResolver.resolveMessage(errorMessageKey)));
        }
        Collections.sort(errors);
        log.debug("Errors reported : {}", errors);
        throw new CreateCartException(errors); //@TODO check with Anwer why this?
    }
}
