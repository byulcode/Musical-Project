package study.musical.infra.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import study.musical.infra.exception.ErrorCode;

@Getter
public class MusicalApiException extends RuntimeException{
    private HttpStatus status;
    private String message;
    private ErrorCode errorCode;

    public MusicalApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public MusicalApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
