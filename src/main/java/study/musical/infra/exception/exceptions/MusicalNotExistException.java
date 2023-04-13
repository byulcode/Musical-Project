package study.musical.infra.exception.exceptions;

import lombok.Getter;
import study.musical.infra.exception.ErrorCode;

@Getter
public class MusicalNotExistException extends RuntimeException{
    private final ErrorCode errorCode;

    public MusicalNotExistException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
