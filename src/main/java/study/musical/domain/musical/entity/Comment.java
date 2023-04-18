package study.musical.domain.musical.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import study.musical.domain.musical.dto.request.CommentRequestDto;
import study.musical.domain.musical.entity.enums.CommentStatus;
import study.musical.infra.entity.BaseTimeEntity;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "parent_id", updatable = false)
    private Long parentId;  //부모 댓글 id

    @Column(name = "comment_content", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_status", nullable = false)
    private CommentStatus commentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musical_id", nullable = false)
    @ToString.Exclude
    private Musical musical;


    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Comment> childComments = new LinkedHashSet<>();

    public void setChildComments(Set<Comment> childComments) {
        this.childComments = childComments;
    }

    public void setMusical(Musical musical) {
        this.musical = musical;
    }

    @Builder
    public Comment(Long parentId, String content, Musical musical, Set<Comment> childComments) {
        this.parentId = parentId;
        this.content = content;
        this.commentStatus = CommentStatus.REGISTERED;
        this.musical = musical;
        this.childComments = childComments;
    }

    public static Comment of(CommentRequestDto requestDto, Musical musical) {
        return Comment.builder()
                .content(requestDto.getContent())
                .musical(musical)
                .build();
    }

    public void addChildComment(Comment child) {
        child.setParentId(this.getId());
        this.getChildComments().add(child);
    }


    public void modifyComment(String content) {
        this.content = content;
    }

    public void delete() {
        this.commentStatus = CommentStatus.DELETED;
    }
}
