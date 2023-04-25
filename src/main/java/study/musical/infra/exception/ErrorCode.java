package study.musical.infra.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NO_TARGET("해당되는 대상이 없습니다.");

    private final String message;
}
