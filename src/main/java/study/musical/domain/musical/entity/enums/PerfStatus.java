package study.musical.domain.musical.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PerfStatus {

    UPCOMING("공연예정"),
    ONGOING("공연중"),
    DONE("종료");

    private final String description;
}
