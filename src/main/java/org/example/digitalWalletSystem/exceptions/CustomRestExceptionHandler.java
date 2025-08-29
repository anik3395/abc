package org.example.digitalWalletSystem.exceptions;
import lombok.extern.slf4j.Slf4j;
import org.example.digitalWalletSystem.common.response.ApiErrorResponse;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
@RestControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    public CustomRestExceptionHandler() {
        super();
    }

    @ExceptionHandler({InvalidDataException.class})
    protected ResponseEntity<Object> handleInvalidDataException(final InvalidDataException ex) {
        ApiResponse<Object> apiError = ApiResponse.fail("Invalid data provided", List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }












}