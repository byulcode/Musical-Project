package study.musical.infra.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.musical.infra.exception.exceptions.CommentNotExistException;
import study.musical.infra.exception.exceptions.MusicalNotExistException;
import study.musical.infra.exception.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MusicalNotExistException.class)
    public ResponseEntity<ErrorResponse> handleMusicalNotExistException(MusicalNotExistException ex) {
        log.error("handleMusicalNotExistException : {}", ex.getErrorCode());
        ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, response.getCode());
    }

    @ExceptionHandler(CommentNotExistException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotExistException(CommentNotExistException exception) {
        log.error("handleCommentNotExistException : {}", exception.getErrorCode());
        ErrorResponse response = new ErrorResponse((exception.getErrorCode()));
        return new ResponseEntity<>(response, response.getCode());
    }
}
