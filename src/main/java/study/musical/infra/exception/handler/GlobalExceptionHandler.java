package study.musical.infra.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import study.musical.infra.exception.ErrorResponse;
import study.musical.infra.exception.exceptions.MusicalApiException;
import study.musical.infra.exception.exceptions.ResourceNotFoundException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception
    ) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MusicalApiException.class)
    public ResponseEntity<ErrorResponse> handleMusicalApiException(MusicalApiException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(new Date())
                .message(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
