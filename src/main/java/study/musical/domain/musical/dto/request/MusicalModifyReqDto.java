package study.musical.domain.musical.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import study.musical.domain.musical.entity.Musical;

import java.time.LocalDate;

@Getter
@ToString
public class MusicalModifyReqDto {
    private String title;
    private String runningTime;
    private int basicPrice;
    private String place;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public MusicalModifyReqDto(String title, String runningTime, int basicPrice, String place, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.runningTime = runningTime;
        this.basicPrice = basicPrice;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Musical toEntity() {
        return Musical.builder()
                .title(title)
                .runningTime(runningTime)
                .basicPrice(basicPrice)
                .place(place)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
