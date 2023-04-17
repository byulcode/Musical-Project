package study.musical.domain.musical.entity;

import lombok.*;
import study.musical.domain.musical.dto.request.MusicalModifyReqDto;
import study.musical.domain.musical.entity.enums.PerfStatus;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "musical")
@ToString(exclude = "comments")
public class Musical extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "perf_status", nullable = false)
    private PerfStatus perfStatus;

    @Column(name = "running_time", nullable = false)
    private String runningTime;

    @Column(name = "basic_price", nullable = false)
    private int basicPrice;

    @Column(name = "list_price")
    private Integer listPrice;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @OneToMany(mappedBy = "musical")
    private List<Comment> comments;

    @Builder
    public Musical(Long id, String title, String runningTime, int basicPrice, Integer listPrice, String place, LocalDate startDate, LocalDate endDate, int likeCount, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.perfStatus = PerfStatus.UPCOMING;
        this.runningTime = runningTime;
        this.basicPrice = basicPrice;
        this.listPrice = listPrice;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.likeCount = 0;
        this.comments = comments;
    }

    public void modify(MusicalModifyReqDto updateDto) {
        this.title = updateDto.getTitle();
        this.runningTime = updateDto.getRunningTime();
        this.basicPrice = updateDto.getBasicPrice();
        this.place = updateDto.getPlace();
        this.startDate = updateDto.getStartDate();
        this.endDate = updateDto.getEndDate();
    }

}
