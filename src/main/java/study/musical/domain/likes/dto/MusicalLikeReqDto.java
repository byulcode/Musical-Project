package study.musical.domain.likes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import study.musical.domain.member.entity.Member;

@Getter
@ToString
public class MusicalLikeReqDto {

    private Member member;
    private Long musicalId;

    @Builder
    public MusicalLikeReqDto(Member member, Long musicalId) {
        this.member = member;
        this.musicalId = musicalId;
    }
}
