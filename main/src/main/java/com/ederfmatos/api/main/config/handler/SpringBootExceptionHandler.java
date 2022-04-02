package com.ederfmatos.api.main.config.handler;

import com.ederfmatos.api.domain.exception.DomainException;
import com.ederfmatos.api.domain.exception.EmailAlreadyRegisteredException;
import com.ederfmatos.api.domain.exception.InvalidParamException;
import com.ederfmatos.api.domain.exception.RequiredFieldException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Order(-2)
@RestControllerAdvice
public class SpringBootExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            RequiredFieldException.class,
            InvalidParamException.class
    })
    public ErrorResponse handleRequiredFieldException(DomainException exception) {
        return ErrorResponse.of(exception);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ErrorResponse handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException exception) {
        return ErrorResponse.of(exception);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyRegisteredException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatus()).body(ErrorResponse.of(exception));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception) {
        exception.printStackTrace();
        return new ErrorResponse("An internal server error occurred");
    }

}
