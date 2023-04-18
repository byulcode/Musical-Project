package study.musical.domain.musical.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentRequestDto {

    private Long parentId;
    private String content;

    public CommentRequestDto(Long parentId, String content) {
        this.parentId = parentId;
        this.content = content;
    }
}
