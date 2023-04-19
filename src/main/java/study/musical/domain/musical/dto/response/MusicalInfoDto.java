package study.musical.domain.musical.dto.response;

import lombok.Builder;
import lombok.Getter;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.entity.enums.PerfStatus;

@Getter
@Builder
public class MusicalInfoDto {

    private final String title;
    private final PerfStatus perfStatus;
    private final int likeCnt;

    public static MusicalInfoDto from(Musical musical) {
        return MusicalInfoDto.builder()
                .title(musical.getTitle())
                .perfStatus(musical.getPerfStatus())
                .likeCnt(musical.getLikes().size())
                .build();
    }
}
