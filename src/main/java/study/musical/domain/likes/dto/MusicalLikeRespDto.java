package study.musical.domain.likes.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MusicalLikeRespDto {

    private final long MusicalLikeCnt;
    private final boolean check; //유저가 해당 뮤지컬에 좋아요를 눌렀는지 아닌지 체크

    public MusicalLikeRespDto(long musicalLikeCnt, boolean check) {
        MusicalLikeCnt = musicalLikeCnt;
        this.check = check;
    }
}
