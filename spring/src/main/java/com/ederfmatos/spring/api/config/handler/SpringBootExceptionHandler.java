package com.ederfmatos.spring.api.config.handler;

import com.ederfmatos.api.domain.exception.DomainException;
import com.ederfmatos.api.domain.exception.EmailAlreadyRegisteredException;
import com.ederfmatos.api.domain.exception.InvalidParamException;
import com.ederfmatos.api.domain.exception.RequiredFieldException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.core.NestedExceptionUtils.getRootCause;

@Order(-2)
@RestControllerAdvice
public class SpringBootExceptionHandler {

    @ExceptionHandler({RequiredFieldException.class, InvalidParamException.class})
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException exception) {
        return makeResponseEntity(HttpStatus.BAD_REQUEST, ErrorResponse.of(exception));
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException exception) {
        return makeResponseEntity(HttpStatus.CONFLICT, ErrorResponse.of(exception));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyRegisteredException(ResponseStatusException exception) {
        return makeResponseEntity(exception.getStatus(), ErrorResponse.of(exception));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        Throwable rootCause = getRootCause(exception);

        if (rootCause instanceof InvalidParamException invalidParamException) {
            return handleDomainException(invalidParamException);
        }

        exception.printStackTrace();
        return makeResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse("An internal server error occurred"));
    }

    private ResponseEntity<ErrorResponse> makeResponseEntity(HttpStatus status, ErrorResponse errorResponse) {
        System.out.printf("Resposta da requisição com erro -> status [%s], body [%s]", status.toString(), errorResponse);
        return ResponseEntity.status(status).body(errorResponse);
    }

}
