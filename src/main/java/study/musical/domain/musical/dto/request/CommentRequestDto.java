package study.musical.domain.musical.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    private Long parentId;
    private String content;
    private String email;

    public CommentRequestDto(Long parentId, String content, String email) {
        this.parentId = parentId;
        this.content = content;
        this.email = email;
    }

}
