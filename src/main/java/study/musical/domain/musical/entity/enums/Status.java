package study.musical.domain.musical.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    REGISTERED("등록됨"),
    DELETED("삭제됨");

    private final String description;
}
