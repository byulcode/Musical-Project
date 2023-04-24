package study.musical.domain.musical.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.musical.domain.likes.entity.Likes;
import study.musical.domain.likes.repository.LikeRepository;
import study.musical.domain.member.entity.Member;
import study.musical.domain.member.repository.MemberRepository;
import study.musical.domain.musical.dto.request.MusicalCreateReqDto;
import study.musical.domain.musical.dto.request.MusicalFindDto;
import study.musical.domain.musical.dto.request.MusicalModifyReqDto;
import study.musical.domain.musical.dto.response.MusicalDetailsDto;
import study.musical.domain.musical.dto.response.MusicalInfoDto;
import study.musical.domain.musical.entity.Musical;
import study.musical.domain.musical.repository.MusicalRepository;
import study.musical.infra.exception.ErrorCode;
import study.musical.infra.exception.exceptions.MusicalNotExistException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicalService {

    private final MusicalRepository musicalRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    /**
     * 뮤지컬 전체 조회(페이징). 뮤지컬 제목, 상태별로 조회 가능
     */
    @Transactional(readOnly = true)
    public Page<MusicalInfoDto> getAllMusicalsPage(MusicalFindDto musicalFindDto) {
        log.info("Musical service getAllMusicalPage run..");
        Page<Musical> musicalPage = musicalRepository.findAllMusicalsPage(musicalFindDto);
        return musicalPage.map(MusicalInfoDto::from);
    }

    /**
     * 뮤지컬 상세보기
     */
    @Transactional(readOnly = true)
    public MusicalDetailsDto getMusicalDetailById(Long id) {
        log.info("Musical service getMusicalDetailById run..");
        Musical musical = getMusicalEntity(id);
        return MusicalDetailsDto.from(musical);
    }

    //해당 멤버가 좋아요한 뮤지컬 목록
    @Transactional(readOnly = true)
    public List<MusicalInfoDto> getAllMusicalsLiked(Long id) {
        log.info("Musical service getAllMusicalsLiked run..");
        Member member = memberRepository.findById(id).orElseThrow();
        List<Musical> musicalList = musicalRepository.findAllMusicalsLiked(getLikes(member));
        log.info("getLikes num : {}", getLikes(member).size());
        return musicalList.stream().map(MusicalInfoDto::from).collect(Collectors.toList());
    }

    private Set<Likes> getLikes(Member member) {
        return likeRepository.findAllByMember(member).orElseThrow();
    }

    /**
     * 뮤지컬 등록(관리자)
     */
    @Transactional
    public void createMusical(MusicalCreateReqDto reqDto) {
        log.info("Musical service createMusical run..");
        Musical musical = reqDto.toEntity();
        log.info("Musical : {}", musical);
        musicalRepository.save(musical);
    }

    /**
     * 뮤지컬 수정
     */
    @Transactional
    public void modifyMusical(Long musicalId, MusicalModifyReqDto reqDto) {
        log.info("Musical service modifyMusical run..");
        Musical musical = getMusicalEntity(musicalId);
        musical.modify(reqDto);
        log.info("Modified Musical : {}", musical);
    }

    private Musical getMusicalEntity(Long musicalId) {
        return musicalRepository.findById(musicalId).orElseThrow(() -> {
            throw new MusicalNotExistException(ErrorCode.MUSICAL_NOT_EXIST);
        });
    }

}
