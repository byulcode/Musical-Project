package study.musical.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MUSICAL_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당 뮤지컬이 존재하지 않습니다."),
    COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "해당 댓글이 존재하지 않습니다.");

    private HttpStatus errorCode;
    private String message;
}
