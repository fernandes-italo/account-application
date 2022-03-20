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

        return this.getErrorMessageResponse(exception.getMessage(), exception.getDescription(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> accountWithoutBalance(AccountWithoutBalanceException exception) {

        return this.getErrorMessageResponse(exception.getMessage(), exception.getDescription(),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> errorDefault(Exception exception) {

        return this.getErrorMessageResponse(exception.getMessage(), ERROR_MESSAGE_DEFAULT,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessageResponse(String message, String description,
            HttpStatus httpStatus) {

        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse(
                httpStatus.value(),
                Instant.now(),
                message,
                description);

        return new ResponseEntity<>(errorMessageResponse, httpStatus);
    }

}
