package study.musical.infra.exception.exceptions;

import lombok.Getter;
import study.musical.infra.exception.ErrorCode;

@Getter
public class CommentNotExistException extends RuntimeException{

    private final ErrorCode errorCode;

    public CommentNotExistException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
