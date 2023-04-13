package study.musical.domain.comment.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import study.musical.domain.comment.entity.enums.CommentStatus;
import study.musical.domain.musical.entity.Musical;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @JoinColumn(name = "musical_id")
    private Musical musical;

    @Builder
    public Comment(Long id, String content, CommentStatus commentStatus, Musical musical) {
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
