package study.musical.domain.musical.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.musical.domain.member.entity.Member;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    private Long parentId;
    private String content;
    private Member member;

    public CommentRequestDto(Long parentId, String content, Member member) {
        this.parentId = parentId;
        this.content = content;
        this.member = member;
    }

}
