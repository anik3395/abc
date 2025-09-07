package org.example.digitalWalletSystem.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    public CustomRestExceptionHandler() {
        super();
    }

    // Handle your custom InvalidDataException
    @ExceptionHandler({InvalidDataException.class})
    protected ResponseEntity<Object> handleInvalidDataException(final InvalidDataException ex) {
        ApiResponse<Object> apiError = ApiResponse.fail("Invalid data provided", List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // Handle Spring Security BadCredentialsException (wrong password)
    @ExceptionHandler({BadCredentialsException.class})
    protected ResponseEntity<Object> handleBadCredentialsException(final BadCredentialsException ex) {
        ApiResponse<Object> apiError = ApiResponse.fail("Invalid data provided", List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    // Optional: Catch-all for other exceptions
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleOtherExceptions(final Exception ex) {
        log.error("Unexpected error: ", ex);
        ApiResponse<Object> apiError = ApiResponse.fail("Something went wrong", List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.put(error.getField(), error.getDefaultMessage());
//        }
//        ApiResponse<Object> apiError = ApiResponse.fail("Validation failed", errors);
//        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//    }
}
