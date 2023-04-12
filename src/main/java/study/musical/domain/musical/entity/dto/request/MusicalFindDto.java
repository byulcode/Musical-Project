package study.musical.domain.musical.entity.dto.request;

import lombok.Builder;
import lombok.Getter;
import study.musical.domain.musical.entity.enums.PerfStatus;

@Getter
@Builder
public class MusicalFindDto {

    private String title;
    private PerfStatus perfStatus;

}