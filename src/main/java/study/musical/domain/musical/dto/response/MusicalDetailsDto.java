package study.musical.domain.musical.dto.response;

import lombok.Builder;
import lombok.Getter;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.entity.enums.PerfStatus;

import java.time.LocalDate;

@Getter
@Builder
public class MusicalDetailsDto {

    private final String title;
    private final String content;
    private final PerfStatus perfStatus;
    private final String runningTime;
    private final int basePrice;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int likeCount;

    public static MusicalDetailsDto from(Musical musical) {
        return MusicalDetailsDto.builder()
                .title(musical.getTitle())
                .content(musical.getContent())
                .perfStatus(musical.getPerfStatus())
                .runningTime(musical.getRunningTime())
                .basePrice(musical.getBasicPrice())
                .startDate(musical.getStartDate())
                .endDate(musical.getEndDate())
                .likeCount(musical.getLikes().size())
                .build();
    }
}
