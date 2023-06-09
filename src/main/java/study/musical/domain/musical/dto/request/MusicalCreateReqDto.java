package study.musical.domain.musical.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.musical.domain.musical.entity.Musical;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
public class MusicalCreateReqDto {

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요")
    private String content;

    @NotBlank(message = "러닝타임을 입력해주세요")
    private String runningTime;

    @NotBlank
    private int basicPrice;

    @NotBlank
    private LocalDate startDate;

    @NotBlank
    private LocalDate endDate;

    @Builder
    public MusicalCreateReqDto(String title, String content, String runningTime, int basicPrice, LocalDate startDate, LocalDate endDate) {
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
