package com.project.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(
            PasswordMismatchException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-0001",
                        "Password entered is incorrect");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(
            UserDoesNotExistException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-0020",
                        "User does not exists " + ex.getId());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionNotAvailableException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(
            TransactionNotAvailableException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-0400",
                        "Transaction with id: " + ex.getId() + " does not exist." );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(
            InsufficientFundsException ex) {
        ApiErrorResponse response =
                new ApiErrorResponse("error-0400",
                        "Insufficient funds in wallet. Recharge with " + ex.getAmt() + " to make the transfer" );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
