package study.musical.domain.comment.entity.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.musical.domain.comment.entity.Comment;

@Getter
@NoArgsConstructor
public class CommentCreateRequest {

    private String content;
    @Builder
    public CommentCreateRequest(String content) {
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
