package com.lightside.fg.web.exception;

import com.lightside.fg.exception.ApplicationException;
import com.lightside.fg.exception.CreateCartException;
import com.lightside.fg.exception.NoRecordFoundException;
import com.lightside.fg.web.locale.MessageResolver;
import com.lightside.fg.web.response.CreateCartResponse;
import com.lightside.fg.web.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

import static com.lightside.fg.web.response.ErrorResponse.buildError;

/**
 * @author Anwar
 */

@ControllerAdvice(basePackages = "com.lightside.fg.web.controller")
@Configurable
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageResolver messageResolver;

    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    ResponseEntity<?> handleException(ApplicationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (CollectionUtils.isNotEmpty(ex.getErrors())) {
            return new ResponseEntity<>(ex.getErrors(), status);
        }
        ErrorResponse errorResponse = buildError(messageResolver.resolveMessage(ex.getErrorKey()), messageResolver.resolveMessage(ex.getMessageKey()));
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<?> handleException(Exception ex) {
        logger.trace(ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = buildError(messageResolver.resolveMessage("system.error.key"),
                messageResolver.resolveMessage("system.error.message"));
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    ResponseEntity<?> handleException(DataIntegrityViolationException ex) {
        logger.trace(ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ConstraintViolationException cve = (ConstraintViolationException) ex.getCause();
        String constraintName = cve.getConstraintName() == null ? "DEFAULT" : cve.getConstraintName();
        ErrorResponse errorResponse = null;
        switch (constraintName) {
//            @TODO we need to really pass arguments to errors
            case "UK_CART_USRID":
                errorResponse = getErrorResponse("cartid.alreadyexists.key", "cartid.alreadyexists.message");
                break;
            case "UK_CARTS_EMAIL":
                errorResponse = getErrorResponse("email.alreadyexists.key", "email.alreadyexists.message");
                break;
            case "UK_USR_CONTACT_NUMBER":
                errorResponse = getErrorResponse("number.alreadyexists.key", "number.alreadyexists.message");
                break;
            case "UK_CART_ITEM_PROD_ID":
                errorResponse = getErrorResponse("product.cart.alreadyexists.key", "product.cart.alreadyexists.message");
                break;
            default:
                errorResponse = getErrorResponse("db.constraint.violation.key", "db.constraint.violation.message");
        }
        if (errorResponse != null) {
            return new ResponseEntity<>(getCreateOrUpdateCartErrorResponse(Collections.singleton(errorResponse)), status);
        }
        return returnDefaultSystemError(status);

    }

    public List<ErrorResponse> createErrors(ObjectError anyError, Object[] params) {
        List<ErrorResponse> errors = new ArrayList<>();

        if (anyError != null) {
            {
                String errorKey = anyError.getDefaultMessage() + ".key";
                String errorMessageKey = anyError.getDefaultMessage() + ".message";
                errors.add(buildError(messageResolver.resolveMessage(errorKey), messageResolver.resolveMessage(errorMessageKey , params)));
            }
            log.debug("Errors reported : {}", errors);

        }
      return errors;
    }

    public List<ErrorResponse> createErrors(ObjectError anyError) {
        return createErrors(anyError , null);
    }


    private ErrorResponse getErrorResponse(String errorCode, String errorMessage) {
        return ErrorResponse
                .buildError(messageResolver
                                .resolveMessage(errorCode),
                        messageResolver
                                .resolveMessage(errorMessage));
    }

    private ResponseEntity<?> returnDefaultSystemError(HttpStatus status) {
        ErrorResponse errorResponse;//Default error
        errorResponse = buildError(messageResolver.resolveMessage("system.error.key"),
                messageResolver.resolveMessage("system.error.message"));
        return new ResponseEntity<>(errorResponse, status);
    }


    @ExceptionHandler(CreateCartException.class)
    @ResponseBody
    ResponseEntity<?> handleException(CreateCartException ex) {
        CreateCartResponse createCartResponse = getCreateOrUpdateCartErrorResponse(ex.getErrors());
        return new ResponseEntity<>(createCartResponse, HttpStatus.BAD_REQUEST);
    }

    private CreateCartResponse getCreateOrUpdateCartErrorResponse(Collection<ErrorResponse> errors) {
        return CreateCartResponse.builder().valid(false).errors(errors).build();
    }

    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseBody
    ResponseEntity<?> handleException(NoRecordFoundException ex) {
        return new ResponseEntity<>(getErrorResponse(ex), HttpStatus.NOT_FOUND);
    }

    private ErrorResponse getErrorResponse(ApplicationException ex) {
        return buildError(messageResolver.resolveMessage(ex.getErrorKey()),
                messageResolver.resolveMessage(ex.getMessageKey()));
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }


}
