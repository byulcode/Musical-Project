package study.musical.domain.musical.dto.request;

import lombok.*;
import study.musical.domain.musical.entity.Musical;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MusicalModifyReqDto {
    private String title;
    private String content;
    private String runningTime;
    private int basicPrice;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public MusicalModifyReqDto(String title, String content, String runningTime, int basicPrice, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.content = content;
        this.runningTime = runningTime;
        this.basicPrice = basicPrice;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Musical toEntity() {
        return Musical.builder()
                .title(title)
                .content(content)
                .runningTime(runningTime)
                .basicPrice(basicPrice)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
