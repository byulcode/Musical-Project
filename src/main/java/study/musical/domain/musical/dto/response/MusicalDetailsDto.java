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
    private final PerfStatus perfStatus;
    private final String runningTime;
    private final int basePrice;
    private final String place;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int likeCount;

    public static MusicalDetailsDto from(Musical musical) {
        return MusicalDetailsDto.builder()
                .title(musical.getTitle())
                .perfStatus(musical.getPerfStatus())
                .runningTime(musical.getRunningTime())
                .basePrice(musical.getBasicPrice())
                .place(musical.getPlace())
                .startDate(musical.getStartDate())
                .endDate(musical.getEndDate())
                .likeCount(musical.getLikeCount())
                .build();
    }
}
