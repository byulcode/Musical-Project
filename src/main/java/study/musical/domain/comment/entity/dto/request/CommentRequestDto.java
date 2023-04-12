package study.musical.domain.comment.entity.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.musical.domain.comment.entity.Comment;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String content;
    @Builder
    public CommentRequestDto(String content) {
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
