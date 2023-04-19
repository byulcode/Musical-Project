package study.musical.domain.likes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.musical.domain.likes.dto.MusicalLikeReqDto;
import study.musical.domain.likes.entity.Likes;
import study.musical.domain.likes.repository.LikeRepository;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.repository.MusicalRepository;
import study.musical.infra.exception.ErrorCode;
import study.musical.infra.exception.exceptions.MusicalNotExistException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MusicalRepository musicalRepository;


    //좋아요 누르기 및 좋아요 취소
    @Transactional
    public Boolean pushLikeButton(MusicalLikeReqDto musicalLikeReqDto) {
        log.info("Like service pushLikeButton run..");
        likeRepository.exist(musicalLikeReqDto.getMember().getId(), musicalLikeReqDto.getMusicalId())
                .ifPresentOrElse(
                        like -> likeRepository.deleteById(musicalLikeReqDto.getMusicalId()),
                        () -> {
                            Musical musical = getMusical(musicalLikeReqDto);
                            likeRepository.save(new Likes(musicalLikeReqDto.getMember(), musical));
                        });
        return true;
    }

    //사용자가 해당 뮤지컬에 좋아요를 눌렀는지 여부
    @Transactional(readOnly = true)
    public boolean checkPushedLike(MusicalLikeReqDto musicalLikeReqDto) {
        log.info("Like service checkPushedLike run..");
        return likeRepository.exist(musicalLikeReqDto.getMember().getId(), musicalLikeReqDto.getMusicalId())
                .isPresent();
    }

    //뮤지컬의 좋아요 개수
    @Transactional(readOnly = true)
    public long getMusicalLikeCnt(MusicalLikeReqDto musicalLikeReqDto) {
        log.info("Like service getMusicalLikeCnt run..");
        return likeRepository.findMusicalLikeCnt(musicalLikeReqDto.getMusicalId());
    }

    @Transactional(readOnly = true)
    public Musical getMusical(MusicalLikeReqDto musicalLikeReqDto) {
        return musicalRepository.findById(musicalLikeReqDto.getMusicalId())
                .orElseThrow(() -> new MusicalNotExistException(ErrorCode.MUSICAL_NOT_EXIST));
    }
}
