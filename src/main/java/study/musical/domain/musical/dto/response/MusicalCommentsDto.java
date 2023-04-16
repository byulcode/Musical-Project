package study.musical.domain.musical.dto.response;

import lombok.Builder;
import lombok.Getter;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.entity.enums.PerfStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MusicalCommentsDto {

    private final Long id;
    private final String title;
    private final PerfStatus perfStatus;
    private final String runningTime;
    private final int basicPrice;
    private final String place;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int likeCount;
    private final List<CommentResponseDto> comments;

    public static MusicalCommentsDto from(Musical musical) {
        return MusicalCommentsDto.builder()
                .id(musical.getId())
                .title(musical.getTitle())
                .perfStatus(musical.getPerfStatus())
                .runningTime(musical.getRunningTime())
                .basicPrice(musical.getBasicPrice())
                .place(musical.getPlace())
                .startDate(musical.getStartDate())
                .endDate(musical.getEndDate())
                .likeCount(musical.getLikeCount())
                .comments(musical.getComment().stream().map(CommentResponseDto::from).collect(Collectors.toList()))
                .build();
    }
}
