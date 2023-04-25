package study.musical.domain.musical.entity;

import lombok.*;
import study.musical.domain.likes.entity.Likes;
import study.musical.domain.musical.dto.request.MusicalModifyReqDto;
import study.musical.domain.musical.entity.enums.PerfStatus;
import study.musical.domain.musical.entity.enums.Status;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "musical")
@ToString
public class Musical extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "musical_status", nullable = false)
    private Status status;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "musical_content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "perf_status", nullable = false)
    private PerfStatus perfStatus;

    @Column(name = "running_time", nullable = false)
    private String runningTime;

    @Column(name = "basic_price", nullable = false)
    private int basicPrice;

    @Column(name = "list_price")
    private Integer listPrice;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "musical")
    @ToString.Exclude
    private Set<Comment> comments;

    @OneToMany(mappedBy = "musical")
    @ToString.Exclude
    private Set<Likes> likes;

    public void setLikes(Set<Likes> likes) {
        this.likes = likes;
    }

    @Builder
    public Musical(Long id, String title, String runningTime, int basicPrice, Integer listPrice, LocalDate startDate, LocalDate endDate, Set<Comment> comments, Set<Likes> likes, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = Status.REGISTERED;
        this.perfStatus = PerfStatus.UPCOMING;
        this.runningTime = runningTime;
        this.basicPrice = basicPrice;
        this.listPrice = listPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.comments = comments;
        this.likes = likes;
    }

    public void modify(MusicalModifyReqDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
        this.runningTime = updateDto.getRunningTime();
        this.basicPrice = updateDto.getBasicPrice();
        this.startDate = updateDto.getStartDate();
        this.endDate = updateDto.getEndDate();
    }

    public void delete() {
        this.status = Status.DELETED;
    }
}
