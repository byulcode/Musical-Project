package study.musical.domain.likes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.musical.domain.likes.entity.Likes;
import study.musical.domain.likes.repository.LikeRepository;
import study.musical.domain.member.entity.Member;
import study.musical.domain.member.repository.MemberRepository;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.repository.MusicalRepository;
import study.musical.infra.exception.ErrorCode;
import study.musical.infra.exception.exceptions.MusicalNotExistException;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MusicalRepository musicalRepository;
    private final MemberRepository memberRepository;


    //좋아요 누르기 및 좋아요 취소
    @Transactional
    public void pushLikeButton(Long musicalId, String email) {
        log.info("Likes service pushLikeButton run..");
        Musical musical = getMusical(musicalId);
        Member member = getMemberByEmail(email);
        log.info("musical : {}", musical);
        log.info("member : {}", member);
        likeRepository.findByMusicalAndMember(musical, member)
                .ifPresentOrElse(
                        like -> likeRepository.deleteLikesByMusicalAndMember(musical, member),
                        () -> likeRepository.save(Likes.builder()
                                .member(member)
                                .musical(musical).build()));
    }

    private Musical getMusical(Long musicalId) {
        return musicalRepository.findById(musicalId)
                .orElseThrow(() -> new MusicalNotExistException(ErrorCode.MUSICAL_NOT_EXIST));
    }

    private Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow();
    }

}
