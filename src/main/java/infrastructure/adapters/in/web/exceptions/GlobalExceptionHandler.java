package infrastructure.adapters.in.web.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import domain.exceptions.UserNotFoundException;
import domain.exceptions.UserRegistrationException;
import infrastructure.exceptions.UserPersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        log.error("User not found, request URI: {}", request.getDescription(false), ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(UserRegistrationException ex, WebRequest request) {
        log.error("User registration failed, request URI: {}", request.getDescription(false), ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserPersistenceException.class)
    public ResponseEntity<ErrorResponse> handleUserPersistenceException(UserPersistenceException ex, WebRequest request) {
        log.error("User persistence failed, request URI: {}", request.getDescription(false), ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        log.error("An error occurred, request URI: {}", request.getDescription(false), ex);
        String genericErrorMessage = "An unexpected error occurred. Please try again later.";
        return new ResponseEntity<>(new ErrorResponse(genericErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    @ExceptionHandler(SpecificException.class)
    public ResponseEntity<ErrorResponse> handleSpecificException(SpecificException ex, WebRequest request) {
        // Custom handling for SpecificException
        // Replace SpecificException with the actual exception class you need to handle
        // Replace HttpStatus.CUSTOM_STATUS with the actual HttpStatus you intend to return
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.CUSTOM_STATUS), HttpStatus.CUSTOM_STATUS);
    }
    */

}

@Getter
@RequiredArgsConstructor
class ErrorResponse {
    private final String message;
    private final HttpStatus status;
    private final long timestamp = System.currentTimeMillis();
}
