package dev.italofernandes.account.api.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.italofernandes.account.application.dto.response.ErrorMessageResponse;
import dev.italofernandes.account.domain.exceptions.AccountNotFoundException;
import dev.italofernandes.account.domain.exceptions.AccountWithoutBalanceException;

@ControllerAdvice
public class ExceptionControllerHandler {

    private final String ERROR_MESSAGE_DEFAULT = "Não foi possível processar sua requisição!";

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> accountNotFound(AccountNotFoundException exception) {

        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                Instant.now(),
                exception.getMessage(),
                exception.getDescription());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> accountWithoutBalance(AccountWithoutBalanceException exception) {

        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                Instant.now(),
                exception.getMessage(),
                exception.getDescription());

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorDefault(RuntimeException exception) {

        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Instant.now(),
                exception.getMessage(),
                ERROR_MESSAGE_DEFAULT);

        return new ResponseEntity<>(errorMessageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
