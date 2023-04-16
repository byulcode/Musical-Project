package study.musical.domain.musical.dto.request;

import lombok.Builder;
import lombok.Getter;
import study.musical.domain.musical.entity.enums.PerfStatus;
import study.musical.infra.utils.pagination.PageRequestDto;

@Getter
@Builder
public class MusicalFindDto extends PageRequestDto {

    private String title;
    private PerfStatus perfStatus;

    @Builder
    public MusicalFindDto(String title, PerfStatus perfStatus) {
        this.title = title;
        this.perfStatus = perfStatus;
    }
}
