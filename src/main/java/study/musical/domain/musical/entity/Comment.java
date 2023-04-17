package study.musical.domain.musical.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import study.musical.domain.musical.entity.enums.CommentStatus;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_status", nullable = false)
    private CommentStatus commentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musical_id", nullable = false)
    private Musical musical;

    @Builder
    public Comment(Long id, String content, Musical musical) {
        this.id = id;
        this.content = content;
        this.commentStatus = CommentStatus.REGISTERED;
        this.musical = musical;
    }

    public void modifyComment(String content) {
        this.content = content;
    }

    public void delete() {
        this.commentStatus = CommentStatus.DELETED;
    }
}
