package study.musical.domain.musical.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    private Long parentId;

    @NotBlank(message = "내용을 입력해 주세요")
    private String content;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    public CommentRequestDto(Long parentId, String content, String email) {
        this.parentId = parentId;
        this.content = content;
        this.email = email;
    }

}
